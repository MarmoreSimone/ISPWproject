package viewcli;

import bean.MenuItemBean;

import java.util.ArrayList;
import java.util.List;

public class CustomizeBeverageViewCli extends viewcli.UtilsCli{

    public void drawTitle(){

        System.out.println("===CUSTOMIZE MENU ITEM====");
    }
    public void drawItemInfo(MenuItemBean item) {

        String[] parts = item.getName().split("\n", 2);
        String firstWord = parts[0];
        System.out.println("name: " + firstWord);
        System.out.println("price: " + item.getPrice() +"$");
        System.out.println("description: " + item.getDescription());
        System.out.println("calories: " + item.getCalories());
        System.out.println("caffeine: " + item.getCaffeine());
        System.out.println("");

    }

    public void drawShowToppings(List<MenuItemBean> items){

        List<String> toppings = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            toppings.add(items.get(i).getName() + " " + items.get(i).getPrice() + "$");
        }
        showChoices(toppings);
    }



}
