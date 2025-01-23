package engineering.decorator;


import model.menuitem.MenuItem;
import model.cafeteria.Cafeteria;

import java.util.List;

public class Toppings extends ItemDecorator{

        List<MenuItem> cafeteriaToppings;
        MenuItem selectedTopping;

        public Toppings(MenuItemComponent item, Cafeteria cafeteria) {

            super(item);
            cafeteriaToppings = cafeteria.getToppings();

        }

        public void applyTopping(String name){
            for(MenuItem item : cafeteriaToppings){
                if(item.getName().equals(name)){
                    selectedTopping = item;
                }
            }
        }

        @Override
        public String getName(){
            String name = super.getName();
            name = name + "\n   +"+selectedTopping.getName();
            return name;
        }

        @Override
        public double getPrice(){
            double price = super.getPrice();
            price = price + selectedTopping.getPrice();

            return Math.round(price*10)/10;
        }

        @Override
        public double getCalories(){
            Double calories = super.getCalories();
            calories = calories + selectedTopping.getCalories();
            return calories;
        }

        @Override
        public double getCaffeine(){
            Double caffeine = super.getCaffeine();
            caffeine = caffeine + selectedTopping.getCaffeine();
            return caffeine;
        }



}
