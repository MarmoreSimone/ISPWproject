package controller;

import bean.BeverageBean;
import bean.CafeteriaBean;
import bean.OrderDetailBean;
import bean.SearchCafeteriaBean;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;
import model.order.Order;
import utils.RetrieveCafeterias;


import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController {

    //caffetteria su cui si sta facendo l'ordine
    private Cafeteria myCafeteria;
    //bevande aggiunte all'ordine
    private List<Beverage> myBeverages;

    private Order order;

    public PlaceOrderController() {
        myBeverages = new ArrayList<>();
    }

    //ritorna una lista di cafeteriaBean andando a filtrare la searchCafeteriaBean
    //uso la searchBean in questo passaggio in quanto non mi interessa mostrare tutte le informazioni della caffetteria nella ricerca
    public List<SearchCafeteriaBean> searchCafeterias(SearchCafeteriaBean key){

        RetrieveCafeterias searcher = new RetrieveCafeterias();

        //lista di searchCafeteriaBean da tornare
        List<SearchCafeteriaBean> foundCafes = new ArrayList<>();

        //caso in cui si vuole cercare per nome
        if(key.getName() != null){
            Cafeteria temp = searcher.getCafeteriaByName(key.getName());
            foundCafes.add(new SearchCafeteriaBean(temp.getName(), temp.getAddress()));
        }

        //caso in cui si vogliono cercare tutte le caffetterie disponibili
        else if(key.getAddress() == null && key.getName() == null){
            for(Cafeteria cafe: searcher.getAllCafeterias()){
                foundCafes.add(new SearchCafeteriaBean(cafe.getName(), cafe.getAddress()));
            }
        }

        //caso in cui si vuole cercare per indirizzo o città
        //TODO ricerca con api google maps

        return foundCafes;
    }

    //ritorna la cafeteriaBean contenente tutti i dati della caffetteria da mostrare
    public CafeteriaBean loadSelectedCafeteria(SearchCafeteriaBean key){

        RetrieveCafeterias searcher = new RetrieveCafeterias();
        Cafeteria tempCafe = searcher.getCafeteriaByName(key.getName());
        return new CafeteriaBean(tempCafe.getName(), tempCafe.getAddress(), tempCafe.getCity(), tempCafe.getNumber(), tempCafe.getDescription(), tempCafe.getOpeningHours(), tempCafe.getPhoto());

    }

    public void setCafeteria(SearchCafeteriaBean key) {
        this.myCafeteria = new RetrieveCafeterias().getCafeteriaByName(key.getName());
    }

    //TODO rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return this.myCafeteria.getName();
    }

    public List<BeverageBean> getCafeteriaBeverages() {

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: myCafeteria.getBeverages()){
            retBeans.add(new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage()));
        }

        return retBeans;
    }

    public void addBeverageToOrder(BeverageBean bev){
        //ne creo direttamente una nuova così da non avere problemi nel caso di bevande personalizzate
        myBeverages.add(new Beverage(bev.getName(),bev.getDescription(),bev.getPrice(),bev.getCalories(),bev.getCaffeine(),bev.getImage()));
    }

    public void removeBeverageFromOrder(BeverageBean bev){
        for(Beverage b: myBeverages){
            if(b.getName().equals(bev.getName())){
                myBeverages.remove(b);
                return;
            }
        }
    }

    public List<BeverageBean> getAddedBev(){

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: myBeverages){
            retBeans.add(new BeverageBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage()));
        }

        return retBeans;

    }

    public Double totalPrice(){
        double tot = 0;
        for(Beverage bev: myBeverages){
            tot = tot + bev.getPrice();
        }

        return tot;
    }

    public void buildOrder(OrderDetailBean details){

        this.order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();

        this.order.setCafeteria(myCafeteria);
        this.order.setItems(myBeverages);
        this.order.setDate(details.getDate());
        this.order.setTime(details.getTime());
        this.order.setNote(details.getNote());
        this.order.setPayMethod(details.getPayMethod());
        this.order.setTotPrice(totalPrice());

    }

    public Order getOrder(){
        return this.order;
    }

}
