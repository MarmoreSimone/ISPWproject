package graphicalcontrollers.customizebeverage;

import bean.BeverageBean;
import controller.PlaceOrderController;
import viewcli.CustomizeBeverageViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomizeBeverageCLI {

    private PlaceOrderController placeOrderController;
    private CustomizeBeverageViewCli view;

    public void launch(String session) {

        placeOrderController = new PlaceOrderController(session);
        view = new CustomizeBeverageViewCli();

        BeverageBean bev = placeOrderController.getCustomBev();
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
