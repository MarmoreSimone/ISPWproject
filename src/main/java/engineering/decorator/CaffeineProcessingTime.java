package engineering.decorator;


public class CaffeineProcessingTime extends ItemDecorator{

        Double weight;

        public CaffeineProcessingTime(MenuItemComponent item) {
            super(item);
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        private double calculateMetabolismTime(double caffeineAmount) {
            // Supponiamo una mezza vita media di 5 ore per la caffeina
            double halfLife = 5;

            // Calcoliamo il fattore di decadimento per ora usando la formula dell'esponenziale
            double decayFactor = Math.pow(0.5, 1 / halfLife);

            // Calcoliamo il tempo necessario per smaltire la caffeina, partendo da una quantità iniziale
            double time = 0;
            double remainingCaffeine = caffeineAmount;

            // Continuare fino a quando la caffeina non è praticamente smaltita (ad esempio meno di 50 mg)
            while (remainingCaffeine > 50) {
                remainingCaffeine *= decayFactor;
                time += 1; // Incrementa il tempo di 1 ora a ogni ciclo
            }

            //stima del metabolism rate
            double metabolismRate = 0.02 * weight;

            time = time / metabolismRate;

            // Restituisci il tempo arrotondato a 1 decimale
            return Math.round(time * 10) / 10.0;
        }

        @Override
        public String getDescription(){
            String descr = super.getDescription();
            descr = descr + "\n+ It will take approximately: " + calculateMetabolismTime(super.getCaffeine()) +" hours for your body to metabolize the caffeine";
            return descr;
        }





}
