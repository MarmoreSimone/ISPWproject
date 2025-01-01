package bean;

import java.util.List;

public class CafeteriaBean {

    private String CafeteriaName;
    private String StreetAddress;
    private String town;
    private String TelephoneNumber;
    private String CafeDescription;
    private List<String> openingHour;
    private String picture;


    public CafeteriaBean(String name, String address, String city, String number, String description, List<String> openingHours, String photo) {
        this.CafeteriaName = name;
        this.StreetAddress = address;
        this.town = city;
        this.TelephoneNumber = number;
        this.CafeDescription = description;
        this.openingHour = openingHours;
        this.picture = photo;
    }

    public String getName() {
        return CafeteriaName;
    }

    public String getAddress() {
        return StreetAddress;
    }

    public String getCity() {
        return town;
    }

    public String getNumber() {
        return TelephoneNumber;
    }

    public String getDescription() {
        return CafeDescription;
    }

    public List<String> getOpeningHours() {
        return openingHour;
    }

    public String getPhoto() {
        return picture;
    }


}
