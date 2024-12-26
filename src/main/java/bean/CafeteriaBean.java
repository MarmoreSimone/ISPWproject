package bean;

import java.util.List;

public class CafeteriaBean {

    private String name;
    private String address;
    private String city;
    private String number;
    private String description;
    private List<String> openingHours;
    private String photo;


    public CafeteriaBean(String name, String address, String city, String number, String description, List<String> openingHours, String photo) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.number = number;
        this.description = description;
        this.openingHours = openingHours;
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

    public String getPhoto() {
        return photo;
    }


}
