package viewcli;

import bean.BeverageBean;
import model.beverage.Beverage;

public class CustomizeBeverageViewCli extends viewcli.UtilsCli{

    public void drawTitle(){

        System.out.println("===CUSTOMIZE BEVERAGE====");
    }
    public void drawBevInfo(BeverageBean bev) {
        System.out.println("name: " + bev.getName());
        System.out.println("price: " + String.valueOf(bev.getPrice())+"$");
        System.out.println("description: " + bev.getDescription());
        System.out.println("calories: " + String.valueOf(bev.getCalories()));
        System.out.println("caffeine: " + String.valueOf(bev.getCaffeine()));
        System.out.println("");
    }
}
