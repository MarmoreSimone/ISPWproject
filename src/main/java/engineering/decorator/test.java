package engineering.decorator;


import model.beverage.Beverage;

public class test {

    public static void main(String[] args){

        MenuItem bev = new Beverage("Americano", "real american coffee", 2, 123, 40, "/images/americano.jpg");
        MenuItem bigbev = new Size(bev);
        RunToBurn run = new RunToBurn(bigbev);
        run.setWeight(70.0);
        CaffeineProcessingTime caff = new CaffeineProcessingTime(run);
        caff.setWeight(60.0);
        System.out.println(caff.getDescription());




    }
}
