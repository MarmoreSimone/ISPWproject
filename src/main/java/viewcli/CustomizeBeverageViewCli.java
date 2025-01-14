package viewcli;

import bean.BeverageBean;

public class CustomizeBeverageViewCli extends viewcli.UtilsCli{

    public void drawTitle(){

        System.out.println("===CUSTOMIZE BEVERAGE====");
    }
    public void drawBevInfo(BeverageBean bev) {
        System.out.println("name: " + bev.getName());
        System.out.println("price: " + bev.getPrice() +"$");
        System.out.println("description: " + bev.getDescription());
        System.out.println("calories: " + bev.getCalories());
        System.out.println("caffeine: " + bev.getCaffeine());
        System.out.println("");
    }
}
