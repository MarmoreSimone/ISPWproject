package graphicalcontrollers.orderbuilder;

import bean.BeverageBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import exception.NoCafeteriasFoundException;
import graphicalcontrollers.customizebeverage.CustomizeBeverageCLI;
import graphicalcontrollers.finalizeorder.FinalizeOrderCLI;
import viewcli.OrdereBuildViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBuilderCLI {

    PlaceOrderController placeOrderController;
    OrdereBuildViewCli view;


    //usato quando torno indietro da finalize
    public void launch(PlaceOrderController contr){
        this.placeOrderController = contr;
        launch(contr.getCafeteriaName());
    }


    public void launch(String cafeteria){
        if(placeOrderController == null) {
            //creo il nuovo controllore applicativo che sarà uasto per tutto questo ordine
            placeOrderController = new PlaceOrderController();
            //imposto caffetteria nel contr appl
            try {
                placeOrderController.setCafeteria(new SearchCafeteriaBean(cafeteria, null));
            } catch (NoCafeteriasFoundException e) {
                e.showException();
            }
        }

        view = new OrdereBuildViewCli();
        view.drawTitle();

        int choice;

        do {
            view.drawTotal(placeOrderController.totalPrice());
            view.drawCurrentBev(placeOrderController.getAddedBev());

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
                    new FinalizeOrderCLI().launch(placeOrderController);
                    choice = -1;
                    break;
            }

        }while(choice != -1);

    }

    public void addBev(){
        List<BeverageBean> bev = placeOrderController.getCafeteriaBeverages();

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
                placeOrderController.addBeverageToOrder(bev.get(choice-1));
                break;

            case 1:
                new CustomizeBeverageCLI().launch(this.placeOrderController,bev.get(choice-1));
                System.out.println("TORNATO QUI");
                break;

            case 2:
                placeOrderController.addBeverageToOrder(bev.get(choice-1));
                addBev();
                break;

            default:
                break;
        }

    }

    public void delBev(){

        List<BeverageBean> bevs = placeOrderController.getAddedBev();
        List<String> list = new ArrayList<>();

        for(BeverageBean bev : bevs){
            list.add(bev.getName() + " " + bev.getPrice() + "$");
        }
        int choice = view.drawDelBev(list);
        if(choice == 0){
            return;
        }

        System.out.println("rimossa");
        placeOrderController.removeBeverageFromOrder(bevs.get(choice-1));

    }

}
