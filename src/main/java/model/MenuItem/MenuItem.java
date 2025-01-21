package model.MenuItem;

import engineering.decorator.MenuItemComponent;

public class MenuItem extends MenuItemComponent {

    private String name;
    private String type;
    private String description;
    private double price;
    private double calories;
    private double caffeine;
    private String image;

    public MenuItem(String name, String description, double price, double calories, double caffeine, String image, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.caffeine = caffeine;
        this.image = image;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
