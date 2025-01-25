package viewcli;

import bean.MenuItemBean;

import java.util.ArrayList;
import java.util.List;

public class OrdereBuildViewCli extends UtilsCli{

    public void drawTotal(Double tot){
        System.out.println("TOTAL: " + tot + "$");
        System.out.println("");
    }

    public void drawCurrentBev(List<MenuItemBean> itemList){

        System.out.println("added beverages:");
        for(MenuItemBean item : itemList){

            String[] parts = item.getName().split("\n", 2);
            String firstWord = parts[0];
            String rest = parts.length > 1 ? parts[1] : "";
            if(!rest.equals("")) System.out.println(firstWord + " " + item.getPrice() + "$\n" + rest);
            else System.out.println(firstWord + " " + item.getPrice() + "$");

        }

        System.out.println("");
    }

    public int drawAddBev(List<String> bevs){
        System.out.println("===BEVERAGES===");
        bevs.addFirst("GO BACK");
        showChoices(bevs);
        return getUserChoice(bevs);
    }

    public void drawTitle(){
        System.out.println("===ADD ITEMS TO THE ORDER===");
    }

    public int drawDelBev(List<MenuItemBean> bevs){

        List<String> items = new ArrayList<>();
        items.addFirst("GO BACK");

        for(MenuItemBean item : bevs){

            String[] parts = item.getName().split("\n", 2);
            String firstWord = parts[0];
            String rest = parts.length > 1 ? parts[1] : "";
            if(!rest.equals("")) items.add(firstWord + " " + item.getPrice() + "$\n" + rest);
            else items.add(firstWord + " " + item.getPrice() + "$");

        }

        System.out.println("\nchose a beverage to remove:");
        showChoices(items);
        return getUserChoice(items);

    }

}
