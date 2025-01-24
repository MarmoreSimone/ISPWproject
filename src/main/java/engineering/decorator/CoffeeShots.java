package engineering.decorator;


public class CoffeeShots extends ItemDecorator{

        private int shots;

        public CoffeeShots(MenuItemComponent item) {
            super(item);
        }

        public void setShots(int shots) {
            this.shots = shots;
        }

        @Override
        public String getName(){
            String name = super.getName();
            name = name + "\n   +" + shots + " coffee shots" ;
            return name;
        }

        @Override
        public double getPrice(){
            Double price = super.getPrice();
            price = price + shots*0.3;
            return price;
        }

        @Override
        public double getCalories(){
            Double calories = super.getCalories();
            calories = calories + shots*3;
            return calories;
        }

        @Override
        public double getCaffeine(){
            Double caffeine = super.getCaffeine();
            caffeine = caffeine + shots*50;
            return caffeine;
        }



}
