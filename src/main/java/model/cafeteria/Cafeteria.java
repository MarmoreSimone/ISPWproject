package model.cafeteria;

import model.beverage.Beverage;

import java.util.ArrayList;
import java.util.List;


public class Cafeteria {

    private String name;
    private String address;
    private String city;
    private String number;
    private String description;
    private List<String> openingHours;
    private String photo;
    private List<Beverage> beverages;

    public Cafeteria(String name, String address, String city, String number, String description, String photo) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.number = number;
        this.description = description;
        this.openingHours = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.photo = photo;
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

   public List<String> getOpeningHours() {
        return openingHours;
   }

    public void setOpeningHours(String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
            this.openingHours.add(mon);
            this.openingHours.add(tue);
            this.openingHours.add(wed);
            this.openingHours.add(thu);
            this.openingHours.add(fri);
            this.openingHours.add(sat);
            this.openingHours.add(sun);
 }

 public void setBeverages(Beverage beverage) {
        this.beverages.add(beverage);
 }

 public List<Beverage> getBeverages() {
        return beverages;
 }

    public String getPhoto() {
            return photo;
    }
}
