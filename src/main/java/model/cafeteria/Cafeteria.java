package model.cafeteria;

import model.MenuItem.MenuItem;
import model.orderrequest.OrderRequest;

import java.util.ArrayList;
import java.util.List;


public class Cafeteria {

    private String name;
    private String address;
    private String city;
    private String number;
    private String description;
    private String photo;
    private List<MenuItem> items;
    private List<MenuItem> toppings;
    private List<OrderRequest> orderRequests;

    public Cafeteria(String name, String address, String city, String number, String description, String photo) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.number = number;
        this.description = description;
        this.items = new ArrayList<>();
        this.toppings = new ArrayList<>();
        this.photo = photo;
        this.orderRequests = new ArrayList<>();
    }

   public String getName() {
        return name;
   }

   public String getAddress() {
        return address;
   }

   public String getCity() {
        return city;
   }

   public String getNumber() {
        return number;
   }

   public String getDescription() {
        return description;
   }

    public void setAllBeverage(List<MenuItem> beverages) {
        this.items = beverages;
 }

    public void setItems(MenuItem beverage) {
        this.items.add(beverage);
    }

    public List<MenuItem> getItems() {
        return items;
 }

    public String getPhoto() {
            return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTopping(MenuItem toppings) {
        this.toppings.add(toppings);
    }

    public List<MenuItem> getToppings() {
        return toppings;
    }

    public List<OrderRequest> getOrderRequests(){
        return orderRequests;
    }

    public void setOrderRequests(List<OrderRequest> req){
        this.orderRequests = req;

    }

}
