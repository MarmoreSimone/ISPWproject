package engineering.decorator;


public class Size extends ItemDecorator{


        public Size(MenuItem item) {
            super(item);
        }

        @Override
        public String getName(){
            String name = super.getName();
            name = name + "\n   +EXTRA LARGE";
            return name;
        }

        @Override
        public double getPrice(){
            Double price = super.getPrice();
            price = price*2;
            return price;
        }

        @Override
        public double getCalories(){
            Double calories = super.getCalories();
            calories = calories*2;
            return calories;
        }

        @Override
        public double getCaffeine(){
            Double caffeine = super.getCaffeine();
            caffeine = caffeine*2;
            return caffeine;
        }



}
