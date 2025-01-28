package bean;

public class CafeteriaBean {

    private String cafeteriaName;
    private String streetAddress;
    private String town;
    private String telephoneNumber;
    private String cafeDescription;
    private String picture;
    private String distance;
    private Long distanceInMeters;


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

    public String getDistance() {
        if(distance == null) return "";
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Long distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }


}