package engineering.decorator;


public class RunToBurn extends ItemDecorator{

        Double weight;

        public RunToBurn(MenuItem item) {
            super(item);
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        private double computeRunningTime(double calories) {
            // Usando la formula: Calorie(Kcal) Bruciate = Peso x Distanza x 0.9
            double distanceInKm = super.getCalories() / (weight * 0.9);
            double speed = 7;
            return Math.round((distanceInKm / speed)*60);
        }

        @Override
        public String getDescription(){
            String descr = super.getDescription();
            descr = descr + "\n + To burn off this beverage, you need to run for: " + computeRunningTime(weight) + " minutes";
            return descr;
        }



}
