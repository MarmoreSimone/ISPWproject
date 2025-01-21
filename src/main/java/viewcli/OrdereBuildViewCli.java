package viewcli;

import bean.MenuItemBean;

import java.util.List;

public class OrdereBuildViewCli extends UtilsCli{

    public void drawTotal(Double tot){
        System.out.println("TOTAL: " + tot + "$");
        System.out.println("");
    }

    public void drawCurrentBev(List<MenuItemBean> bevList){

        System.out.println("added beverages:");
        for(MenuItemBean bev : bevList){
            System.out.println(bev.getName() + " " + bev.getPrice() + "$");
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

    public int drawDelBev(List<String> bevs){

        System.out.println("chose a beverage to remove");
        bevs.addFirst("GO BACK");
        showChoices(bevs);
        return getUserChoice(bevs);

    }

}
