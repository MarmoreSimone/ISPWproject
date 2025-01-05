package graphicalcontrollers.customizebeverage;

import bean.BeverageBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import graphicalcontrollers.orderbuilder.OrderBuilderCLI;
import viewcli.CustomizeBeverageViewCli;
import viewcli.OrdereBuildViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomizeBeverageCLI {

    private PlaceOrderController placeOrderController;
    private CustomizeBeverageViewCli view;

    public void launch(PlaceOrderController placeOrderController, BeverageBean bev) {
        this.placeOrderController = placeOrderController;
        view = new CustomizeBeverageViewCli();

        view.drawTitle();
        view.drawBevInfo(bev);

        List<String> choices = new ArrayList<>(Arrays.asList("go back", "add custom beverage"));
        view.showChoices(choices);
        int choice = view.getUserChoice(choices);

        switch (choice) {
            case 0:
                break;

            case 1:
                System.out.println("not available");
                break;
        }
    }
}
