package engineering.decorator;

import graphicalcontrollers.login.LoginCLI;
import graphicalcontrollers.popup.PopupFactory;
import model.DAOfactory;
import model.beverage.Beverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
