package controller;

import bean.*;
import engineering.ControllerSessionManager;
import engineering.PlaceOrderSession;
import engineering.decorator.*;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import exception.WrongFormatException;
import model.DAOfactory;
import model.MenuItem.MenuItem;
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

    // rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return session.getMyCafeteria().getName();
    }

    //ritorna una lista di BeverageBean della caffetteria settata nel contr. appl.
    public List<MenuItemBean> getCafeteriaItems() {

        List<MenuItemBean> retBeans = new ArrayList<>();

        for(MenuItem bev: session.getMyCafeteria().getBeverages()){
            if(!bev.getType().equals("toppings")) retBeans.add(new MenuItemBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage(), bev.getType()));
        }

        return retBeans;
    }

    //aggiunge una bevanda all'ordine
    public void addItemToOrder(MenuItemBean bev){
        //ne creo direttamente una nuova così da non avere problemi nel caso di bevande personalizzate
        List<MenuItem> listBev = session.getMyBeverages();
        listBev.add(new MenuItem(bev.getName(),bev.getDescription(),bev.getPrice(),bev.getCalories(),bev.getCaffeine(),bev.getImage(), bev.getTypes()));
        session.setMyBeverages(listBev);
    }

    //rimuove una bevanda dall'ordine
    public void removeItemFromOrder(MenuItemBean bev){
        List<MenuItem> listBev = session.getMyBeverages();
        for(MenuItem b: listBev){
            if(b.getName().equals(bev.getName())){
                System.out.println("rimuovo " + b.getName());
                listBev.remove(b);
                session.setMyBeverages(listBev);
                return;
            }
        }
    }

    //ritorna una lista di BevBean contenente tutte le bevande aggiunte all'ordine
    public List<MenuItemBean> getAddedItems(){

        BeanUtils beanUtils = new BeanUtils();
        return beanUtils.getBeveragesBeanList(session.getMyBeverages());

    }

    //ritorna il prezzo totale dell'ordine
    public Double totalPrice(){
        double tot = 0;
        List<MenuItem> bevList = session.getMyBeverages();
        for(MenuItem bev: bevList){
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

            if(!details.getTime().matches("^[0-2]?\\d:[0-5]\\d$")){
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


    public void setCustomBev(MenuItemBean bev){

        List<MenuItem> bevList = session.getMyCafeteria().getBeverages();

        for (MenuItem beverage : bevList) {
            if (bev.getName().equals(beverage.getName())) {
                //non gli paso direttamente l'istanza ma faccio una copia così da poter modificare la bevanda senza problemi
                this.session.setCustomBev(new MenuItem(beverage.getName(), beverage.getDescription(), beverage.getPrice(), beverage.getCalories(), beverage.getCaffeine(), beverage.getImage(),bev.getTypes()));
            }
        }



    }

    public MenuItemBean getCustomBev(){
        MenuItem bev = this.session.getCustomBev();
        return new MenuItemBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage(), bev.getType());
    }

    public MenuItemBean customizeMenuItem(CustomOptionsBean bean){

        MenuItemComponent finalItem = this.session.getCustomBev();

        if(bean.getCoffeeShots() > 0){
            CoffeeShots coffeeShot = new CoffeeShots(finalItem);
            coffeeShot.setShots(bean.getCoffeeShots());
            finalItem = coffeeShot;

        }

        if(!bean.getTopping().isEmpty()) {
            for (int i = 0; i < bean.getTopping().size(); i++) {
                Toppings topp = new Toppings(finalItem, this.session.getMyCafeteria());
                topp.applyTopping(bean.getTopping().get(i));
                finalItem = topp;
            }
        }


       if(bean.caffeineInfo() > 0){
           CaffeineProcessingTime caff = new CaffeineProcessingTime(finalItem);
           caff.setWeight(bean.caffeineInfo());
           finalItem = caff;
       }

       if(bean.caloriesInfo() > 0){
           RunToBurn run = new RunToBurn(finalItem);
           run.setWeight(bean.caloriesInfo());
           finalItem = run;

       }

        return new MenuItemBean(finalItem.getName(), finalItem.getDescription(),finalItem.getPrice(),finalItem.getCalories(), finalItem.getCaffeine(), finalItem.getImage(),this.session.getCustomBev().getType());

    }


    public List<MenuItemBean> retrieveToppings(){
        BeanUtils beanUtils = new BeanUtils();
        return beanUtils.getBeveragesBeanList(this.session.getMyCafeteria().getToppings());
    }
}
