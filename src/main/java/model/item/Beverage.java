package model.item;

import engineering.decorator.MenuItem;

public class Beverage extends MenuItem {

    private String name;
    private String description;
    private double price;
    private double calories;
    private double caffeine;
    private String image;

    public Beverage(String name, String description, double price, double calories, double caffeine, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.caffeine = caffeine;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
