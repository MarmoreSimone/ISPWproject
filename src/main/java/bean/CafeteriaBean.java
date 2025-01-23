package bean;

public class CafeteriaBean {

    private String cafeteriaName;
    private String streetAddress;
    private String town;
    private String telephoneNumber;
    private String cafeDescription;
    private String picture;


    public CafeteriaBean(String name, String address, String city, String number, String description,  String photo) {
        this.cafeteriaName = name;
        this.streetAddress = address;
        this.town = city;
        this.telephoneNumber = number;
        this.cafeDescription = description;
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


    public String getPhoto() {
        return picture;
    }


}
