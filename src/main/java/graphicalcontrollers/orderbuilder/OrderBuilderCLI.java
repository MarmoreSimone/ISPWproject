package graphicalcontrollers.orderbuilder;

import bean.MenuItemBean;
import controller.PlaceOrderController;
import graphicalcontrollers.customizebeverage.CustomizeBeverageCLI;
import graphicalcontrollers.finalizeorder.FinalizeOrderCLI;
import viewcli.OrdereBuildViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBuilderCLI {

    private PlaceOrderController placeOrderController;
    private OrdereBuildViewCli view;
    private String session;

    public void launch(String session){

        placeOrderController = new PlaceOrderController(session);
        this.session = session;

        view = new OrdereBuildViewCli();
        view.drawTitle();

        int choice;

        do {
            view.drawTotal(placeOrderController.totalPrice());
            view.drawCurrentBev(placeOrderController.getAddedItems());

            List<String> choices = new ArrayList<>(Arrays.asList("add beverage", "remove beverage", "continue"));
            view.showChoices(choices);
            choice = view.getUserChoice(choices);

            switch (choice) {
                case 0:
                    addBev();
                    break;

                case 1:
                    delBev();
                    break;

                case 2:
                    new FinalizeOrderCLI().launch(session);
                    choice = -1;
                    break;

                default:
                    break;

            }

        }while(choice != -1);

    }

    public void addBev(){
        List<MenuItemBean> bev = placeOrderController.getCafeteriaItems();

        List<String> list = new ArrayList<String>();
        for(int i = 0; i<bev.size(); i++){
            list.add(bev.get(i).getName() + " " + bev.get(i).getPrice() + "$");
        }

        int choice = view.drawAddBev(list);

        if(choice == 0){
            return;
        }

        List<String> choices = new ArrayList<>(Arrays.asList("confirm", "customize this beverage", "add another beverage"));
        view.showChoices(choices);
        int choice2 = view.getUserChoice(choices);

        switch(choice2){

            case 0:
                //-1 in quanto all'indice 0 c'è il go back
                placeOrderController.addItemToOrder(bev.get(choice-1));
                break;

            case 1:
                placeOrderController.setCustomBev(bev.get(choice-1));
                new CustomizeBeverageCLI().launch(session);
                break;

            case 2:
                placeOrderController.addItemToOrder(bev.get(choice-1));
                addBev();
                break;

            default:
                break;
        }

    }

    public void delBev(){

        List<MenuItemBean> bevs = placeOrderController.getAddedItems();
        List<String> list = new ArrayList<>();

        for(MenuItemBean bev : bevs){
            list.add(bev.getName() + " " + bev.getPrice() + "$");
        }
        int choice = view.drawDelBev(list);
        if(choice == 0){
            return;
        }

        System.out.println("rimossa");
        placeOrderController.removeItemFromOrder(bevs.get(choice-1));

    }

}
