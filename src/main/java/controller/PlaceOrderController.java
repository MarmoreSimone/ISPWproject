package controller;

import bean.*;
import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;
import model.order.Order;
import utils.RetrieveCafeterias;
import utils.enums.OrderStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceOrderController {

    //caffetteria su cui si sta facendo l'ordine
    private Cafeteria myCafeteria;
    //bevande aggiunte all'ordine
    private List<Beverage> myBeverages;
    //entity dell'ordine costruita negli ultimi step
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

    //imposta la caffetteria su cui si sta facendo l'ordine nel contr. appl.
    public void setCafeteria(SearchCafeteriaBean key) {
        this.myCafeteria = new RetrieveCafeterias().getCafeteriaByName(key.getName());
    }

    //TODO rivedi se passare una bean, anche solo per il nome
    public String getCafeteriaName() {
        return this.myCafeteria.getName();
    }

    //ritorna una lista di BeverageBean della caffetteria settata nel contr. appl.
    public List<BeverageBean> getCafeteriaBeverages() {

        List<BeverageBean> retBeans = new ArrayList<>();

        for(Beverage bev: myCafeteria.getBeverages()){
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

        this.order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();

        this.order.setCafeteria(myCafeteria);
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

    //ritorna una bean dell'ordine
    public OrderBean getOrder(){
        return new OrderBean(getAddedBev(),loadSelectedCafeteria(new SearchCafeteriaBean(myCafeteria.getName(), myCafeteria.getAddress())), totalPrice(), this.order.getPickUpCode(), this.order.getPayMethod(), this.order.getNote(), this.order.getDate(),this.order.getTime());
    }

    public void sendOrder(){
        this.order.setStatus("PENDING");
        DAOfactory.getDAOfactory().createOrderDAO().saveOrder(this.order);
    }


}
