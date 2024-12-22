package bean;

import java.util.Map;

public class CafeteriaBean {

    private String name;
    private String address;
    private String city;
    private String number;
    private String description;
    private Map<String, String> openingHours;


    public CafeteriaBean(String name, String address, String city, String number, String description) {
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

    public Map<String, String> getOpeningHours() {
        return openingHours;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
}
