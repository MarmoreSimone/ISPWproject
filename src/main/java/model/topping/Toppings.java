package model.topping;


public class Toppings {

    private String name;
    private double price;
    private double calories;
    private double caffeine;

    public Toppings(String name, double price, double calories, double caffeine) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.caffeine = caffeine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }

}
