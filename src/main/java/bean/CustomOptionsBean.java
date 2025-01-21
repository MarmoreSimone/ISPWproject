package bean;

import java.util.List;

public class CustomOptionsBean {

    private List<String> topping;
    private int coffeeShots;
    private double caffeineInfo;
    private double caloriesInfo;

    public CustomOptionsBean(List<String> toppings, int coffeeShots, double caffeineInfo, double caloriesInfo) {
        this.topping = toppings;
        this.coffeeShots = coffeeShots;
        this.caffeineInfo = caffeineInfo;
        this.caloriesInfo = caloriesInfo;
    }

    public List<String> getTopping() {
        return topping;
    }

    public int getCoffeeShots() {
        return coffeeShots;
    }

    public double caffeineInfo() {
        return caffeineInfo;
    }

    public double caloriesInfo() {
        return caloriesInfo;
    }

}
