package controller;

import bean.*;
import engineering.ControllerSessionManager;
import engineering.PlaceOrderSession;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import exception.WrongFormatException;
import model.DAOfactory;
import model.beverage.Beverage;
import model.order.Order;
import model.orderrequest.OrderRequest;
import utils.UserLogged;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceOrderController {

    private Random random;
    private PlaceOrderSession session;

    public PlaceOrderController(String key) {
        this.session = ControllerSessionManager.getInstance().getPlaceOrderSession(key);
        random = new Random();
    }

    //imposta la caffetteria su cui si sta facendo l'ordine nel contr. appl.
    public void setCafeteria(String name)  throws SystemErrorException{
        SearchCafeteriaController search = new SearchCafeteriaController();
        try {
            session.setMyCafeteria(search.getCafeteriaByName(name));
        } catch (NoCafeteriasFoundException e) {
            throw new SystemErrorException(e);
        }
    }

    //TODO rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return session.getMyCafeteria().getName();
    }

    //ritorna una lista di BeverageBean della caffetteria settata nel contr. appl.
    public List<BeverageBean> getCafeteriaBeverages() {

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: session.getMyCafeteria().getBeverages()){
            retBeans.add(new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage()));
        }

        return retBeans;
    }

    //aggiunge una bevanda all'ordine
    public void addBeverageToOrder(BeverageBean bev){
        //ne creo direttamente una nuova così da non avere problemi nel caso di bevande personalizzate
        List<Beverage> listBev = session.getMyBeverages();
        listBev.add(new Beverage(bev.getName(),bev.getDescription(),bev.getPrice(),bev.getCalories(),bev.getCaffeine(),bev.getImage()));
        session.setMyBeverages(listBev);
    }

    //rimuove una bevanda dall'ordine
    public void removeBeverageFromOrder(BeverageBean bev){
        List<Beverage> listBev = session.getMyBeverages();
        for(Beverage b: listBev){
            if(b.getName().equals(bev.getName())){
                listBev.remove(b);
                session.setMyBeverages(listBev);
                return;
            }
        }
    }

    //ritorna una lista di BevBean contenente tutte le bevande aggiunte all'ordine
    public List<BeverageBean> getAddedBev(){

        BeanUtils beanUtils = new BeanUtils();
        return beanUtils.getBeveragesBeanList(session.getMyBeverages());

    }

    //ritorna il prezzo totale dell'ordine
    public Double totalPrice(){
        double tot = 0;
        List<Beverage> bevList = session.getMyBeverages();
        for(Beverage bev: bevList){
            tot = tot + bev.getPrice();
        }

        return tot;
    }

    //costruisce la entity ordine partendo dai details passati come parametri e usando gli attributi nel contr. appl
    public void buildOrder(OrderDetailBean details) throws WrongFormatException {

        LocalDate date;
        LocalDate today;

            date = LocalDate.parse(details.getDate());
            today = LocalDate.now();

            if(details.getDate().isEmpty()){
                throw new WrongFormatException(": select a date");
            }

            if(details.getTime().isEmpty()){
                throw new WrongFormatException(": select the time");
            }

            if(date.isBefore(today)){
                throw new WrongFormatException(": cannot place an order in the past");
            }

            if(!details.getTime().matches("^[0-2]?[0-9]:[0-5][0-9]$")){
                throw new WrongFormatException(": wrong time format, should be 12:00");
            }

            if(details.getPayMethod().isEmpty()){
                throw new WrongFormatException(": select a payment method method");
            }

            Order order = session.getOrder();

            order.setItems(session.getMyBeverages());
            order.setDate(details.getDate());
            order.setTime(details.getTime());
            order.setNote(details.getNote());
            order.setPayMethod(details.getPayMethod());
            order.setTotPrice(totalPrice());

            session.setOrder(order);

    }

    //ritorna una bean dell'ordine che si sta creando
    public OrderBean getMyOrder() {

        BeanUtils beanUtils = new BeanUtils();
        return beanUtils.getOrderBean(session.getOrder());

    }

    public void sendOrderRequest(){

        OrderRequest orderRequest = DAOfactory.getDAOfactory().createOrderRequestDAO().createNewOrder();
        orderRequest.setStatus("PENDING");
        orderRequest.setCafeteria(session.getMyCafeteria());
        orderRequest.setOrder(session.getOrder());
        orderRequest.setUser(UserLogged.getInstance().getUser().getUsername());
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        orderRequest.setPickUpCode(randomString.toString());

        DAOfactory.getDAOfactory().createOrderRequestDAO().saveOrderRequest(orderRequest);

    }

    //ritorna tutti gli ordini piazzati dall'utente
    public List<OrderRequestBean> getAllMyOrderReq() throws SystemErrorException {

        List<OrderRequestBean> retBeans = new ArrayList<>();
        List<OrderRequest> ordReq = DAOfactory.getDAOfactory().createOrderRequestDAO().getAllOrderRequestsByUsername(UserLogged.getInstance().getUser().getUsername());

        BeanUtils beanUtils = new BeanUtils();

        for(int i=0; i<ordReq.size(); i++){

                retBeans.add(beanUtils.getOrdReqBean(ordReq.get(i)));
        }

        return retBeans;

    }

    public List<OrderRequestBean> getAllMyOrderReq(String filter) throws SystemErrorException{
        List<OrderRequestBean> reqBeans = new ArrayList<>(getAllMyOrderReq());

        if(filter.charAt(0) == 'R'){
            System.out.println("sto cercando rejected");
            for (int i = reqBeans.size() - 1; i >= 0; i--) {
                if (reqBeans.get(i).getState().equals("PENDING") || reqBeans.get(i).getState().equals("ACCEPTED")) {
                    reqBeans.remove(i);
                }
            }

        }else {

            for (int i = reqBeans.size() - 1; i >= 0; i--) {
                if (!reqBeans.get(i).getState().equals(filter)) {
                    reqBeans.remove(i);
                }
            }
        }
        return reqBeans;

    }

    public void setCustomBev(BeverageBean bev){

        List<Beverage> bevList = session.getMyCafeteria().getBeverages();

        for (Beverage beverage : bevList) {
            if (bev.getName().equals(beverage.getName())) {
                //non gli paso direttamente l'istanza ma faccio una copia così da poter modificare la bevanda senza problemi
                this.session.setCustomBev(new Beverage(beverage.getName(), beverage.getDescription(), beverage.getPrice(), beverage.getCalories(), beverage.getCaffeine(), beverage.getImage()));
            }
        }



    }

    public BeverageBean getCustomBev(){
        Beverage bev = this.session.getCustomBev();
        return new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage());
    }

}
