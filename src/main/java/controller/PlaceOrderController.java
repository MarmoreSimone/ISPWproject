package controller;

import bean.*;
import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;
import model.order.Order;
import model.orderrequest.OrderRequest;
import model.user.User;
import utils.UserLogged;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceOrderController {

    private User user;
    private Cafeteria myCafeteria;
    //bevande aggiunte all'ordine
    private List<Beverage> myBeverages;
    //entity dell'ordine costruita negli ultimi step
    private Order order;
    private Random random;

    public PlaceOrderController() {
        myBeverages = new ArrayList<>();
        order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();
        user = UserLogged.getInstance().getUser();
        random = new Random();
    }

    //imposta la caffetteria su cui si sta facendo l'ordine nel contr. appl.
    public void setCafeteria(SearchCafeteriaBean key) {
        SearchCafeteriaController search = new SearchCafeteriaController();
        this.myCafeteria = search.getCafeteriaByName(key.getName());
    }

    //TODO rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return myCafeteria.getName();
    }

    //ritorna una lista di BeverageBean della caffetteria settata nel contr. appl.
    public List<BeverageBean> getCafeteriaBeverages() {

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: this.myCafeteria.getBeverages()){
            retBeans.add(new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage()));
        }

        return retBeans;
    }

    //aggiunge una bevanda all'ordine
    public void addBeverageToOrder(BeverageBean bev){
        //ne creo direttamente una nuova così da non avere problemi nel caso di bevande personalizzate
        myBeverages.add(new Beverage(bev.getName(),bev.getDescription(),bev.getPrice(),bev.getCalories(),bev.getCaffeine(),bev.getImage()));
    }

    //rimuove una bevanda dall'ordine
    public void removeBeverageFromOrder(BeverageBean bev){
        for(Beverage b: myBeverages){
            if(b.getName().equals(bev.getName())){
                myBeverages.remove(b);
                return;
            }
        }
    }

    //data una lista di beverages le converte in beveragesBean
    public List<BeverageBean> getBeveragesBeanList(List<Beverage> beverages){
        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: beverages){
            retBeans.add(new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage()));
        }

        return retBeans;

    }

    //ritorna una lista di BevBean contenente tutte le bevande aggiunte all'ordine
    public List<BeverageBean> getAddedBev(){
        return getBeveragesBeanList(myBeverages);
    }

    //ritorna il prezzo totale dell'ordine
    public Double totalPrice(){
        double tot = 0;
        for(Beverage bev: myBeverages){
            tot = tot + bev.getPrice();
        }

        return tot;
    }

    //costruisce la entity ordine partendo dai details passati come parametri e usando gli attributi nel contr. appl
    public void buildOrder(OrderDetailBean details){

        this.order.setItems(myBeverages);
        this.order.setDate(details.getDate());
        this.order.setTime(details.getTime());
        this.order.setNote(details.getNote());
        this.order.setPayMethod(details.getPayMethod());
        this.order.setTotPrice(totalPrice());

    }

    //ritorna una bean dell'ordine che si sta creando
    public OrderBean getMyOrder() {
        return getOrderBean(this.order);
    }

    //dato un Order generico ritorna la Bean
    public OrderBean getOrderBean(Order ord){

        OrderBean bean = new OrderBean();
        bean.setBevs(getBeveragesBeanList(ord.getBevs()));
        bean.setDate(ord.getDate());
        bean.setTime(ord.getTime());
        bean.setNote(ord.getNote());
        bean.setTotPrice(ord.getTotPrice());
        bean.setPayMethod(ord.getPayMethod());
        return bean;
    }


    public void sendOrderRequest(){
        OrderRequest orderRequest = DAOfactory.getDAOfactory().createOrderRequestDAO().createNewOrder();
        orderRequest.setStatus("PENDING");
        orderRequest.setCafeteria(this.myCafeteria);
        orderRequest.setOrder(this.order);
        orderRequest.setUser(user.getUsername());

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
    public List<OrderRequestBean> getAllMyOrderReq(){

        List<OrderRequestBean> retBeans = new ArrayList<>();
        List<OrderRequest> ordReq = DAOfactory.getDAOfactory().createOrderRequestDAO().getAllOrderRequestsByUsername(user.getUsername());

        for(int i=0; i<ordReq.size(); i++){
                retBeans.add(getOrdReqBean(ordReq.get(i)));
        }

        return retBeans;

    }

    public List<OrderRequestBean> getAllMyOrderReq(String filter){
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


    public OrderRequestBean getOrdReqBean(OrderRequest ord){
        OrderRequestBean bean = new OrderRequestBean();

        bean.setCafe(ord.getCafeteria().getName());
        bean.setCode(ord.getPickUpCode());
        bean.setState(ord.getStatus());
        bean.setOrder(getOrderBean(ord.getOrder()));
        bean.setUser(ord.getUser());

        return bean;

    }


}
