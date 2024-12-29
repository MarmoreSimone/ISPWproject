package controller;

import bean.*;
import model.DAOfactory;
import model.beverage.Beverage;
import model.order.Order;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceOrderController {

    //bevande aggiunte all'ordine
    private List<Beverage> myBeverages;
    //entity dell'ordine costruita negli ultimi step
    private Order order;

    public PlaceOrderController() {
        myBeverages = new ArrayList<>();
        order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();
    }

    //imposta la caffetteria su cui si sta facendo l'ordine nel contr. appl.
    public void setCafeteria(SearchCafeteriaBean key) {
        SearchCafeteria search = new SearchCafeteria();
        this.order.setCafeteria(search.getCafeteriaByName(key.getName()));
        System.out.println(this.order.getCafeteria().getName());
    }

    //TODO rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return this.order.getCafeteria().getName();
    }

    //ritorna una lista di BeverageBean della caffetteria settata nel contr. appl.
    public List<BeverageBean> getCafeteriaBeverages() {

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: this.order.getCafeteria().getBeverages()){
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

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        this.order.setPickUpCode(randomString.toString());
    }

    //ritorna una bean dell'ordine che si sta creando
    public OrderBean getMyOrder() {
        return getOrder(this.order);
    }

    //dato un Order generico ritorna la Bean
    public OrderBean getOrder(Order ord){
        SearchCafeteria search = new SearchCafeteria();
        OrderBean bean = new OrderBean();
        bean.setBevs(getBeveragesBeanList(ord.getBevs()));
        bean.setStatus(ord.getStatus());
        bean.setDate(ord.getDate());
        bean.setTime(ord.getTime());
        bean.setNote(ord.getNote());
        bean.setTotPrice(ord.getTotPrice());
        bean.setPayMethod(ord.getPayMethod());
        bean.setPickUpCode(ord.getPickUpCode());
        bean.setCafeteria(search.loadSelectedCafeteria(new SearchCafeteriaBean(ord.getCafeteria().getName(),ord.getCafeteria().getAddress())));

        return bean;
    }

    public void sendOrder(){
        this.order.setStatus("PENDING");
        DAOfactory.getDAOfactory().createOrderDAO().saveOrder(this.order);
    }

    public List<OrderBean> getAllOrders(){

        List<OrderBean> retBeans = new ArrayList<>();
        List<Order> orders = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();
        for(Order o: orders){

            OrderBean bean = getOrder(o);
            retBeans.add(bean);

        }

        return retBeans;

    }


}
