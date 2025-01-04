package bean;

import java.util.List;

public class CafeteriaBean {

    private String cafeteriaName;
    private String streetAddress;
    private String town;
    private String telephoneNumber;
    private String cafeDescription;
    private List<String> openingHour;
    private String picture;


    public CafeteriaBean(String name, String address, String city, String number, String description, List<String> openingHours, String photo) {
        this.cafeteriaName = name;
        this.streetAddress = address;
        this.town = city;
        this.telephoneNumber = number;
        this.cafeDescription = description;
        this.openingHour = openingHours;
        this.picture = photo;
    }

    public String getName() {
        return cafeteriaName;
    }

    public String getAddress() {
        return streetAddress;
    }

    public String getCity() {
        return town;
    }

    public String getNumber() {
        return telephoneNumber;
    }

    public String getDescription() {
        return cafeDescription;
    }

    public List<String> getOpeningHours() {
        return openingHour;
    }

    public String getPhoto() {
        return picture;
    }


}
