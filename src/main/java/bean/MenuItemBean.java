package bean;

public class MenuItemBean {
    private String bevName;
    private String descr;
    private double cost;
    private double calories;
    private double caffeineValue;
    private String pic;
    private String types;

    public MenuItemBean(String name, String description, double price, double calories, double caffeine, String image, String types) {
        this.bevName = name;
        this.descr = description;
        this.cost = price;
        this.calories = calories;
        this.caffeineValue = caffeine;
        this.pic = image;
        this.types = types;
    }

    public String getName() {
        return bevName;
    }

    public void setName(String name) {
        this.bevName = name;
    }

    public String getDescription() {
        return descr;
    }

    public void setDescription(String description) {
        this.descr = description;
    }

    public double getPrice() {
        return cost;
    }

    public void setPrice(double price) {
        this.cost = price;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCaffeine() {
        return caffeineValue;
    }

    public void setCaffeine(double caffeine) {
        this.caffeineValue = caffeine;
    }

    public String getImage() {
        return pic;
    }

    public void setImage(String image) {
        this.pic = image;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
