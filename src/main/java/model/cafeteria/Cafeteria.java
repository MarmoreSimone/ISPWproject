package model.cafeteria;

import java.util.Map;

public class Cafeteria {

    private String name;
    private String address;
    private String city;
    private String number;
    private String description;

    //la prima stringa è il giorno della settimana la seconda l'orario di apertura nel formato 07:00 - 20:00
    private Map<String, String> openingHours;

    public Cafeteria(String name, String address, String city, String number, String description) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.number = number;
        this.description = description;
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

   public Map<String, String> getOpeningHours() {
        return openingHours;
   }



}
