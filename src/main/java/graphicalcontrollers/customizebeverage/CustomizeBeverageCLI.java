package graphicalcontrollers.customizebeverage;

import bean.CustomOptionsBean;
import bean.MenuItemBean;
import controller.PlaceOrderController;
import viewcli.CustomizeBeverageViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomizeBeverageCLI {

    private PlaceOrderController placeOrderController;
    private CustomizeBeverageViewCli view;
    private MenuItemBean item;

    public void launch(String session) {

        placeOrderController = new PlaceOrderController(session);
        view = new CustomizeBeverageViewCli();
        item = placeOrderController.getCustomBev();
        List<MenuItemBean> toppings = placeOrderController.retrieveToppings();

        List<String> toppingsName = new ArrayList<>();
        for (MenuItemBean topp : toppings) {
            toppingsName.add(topp.getName());
        }




        view.drawTitle();
        view.drawItemInfo(item);
        List<String> choices = new ArrayList<>(Arrays.asList("go back", "add customized item","customize item", "get more info about caffeine", "get more info about calories", "add coffee shots", "add topping" ));
        view.showChoices(choices);

        int choice;
        double caff=0;
        double cal=0;
        int shots = 0;
        List<String> chooseToppings = new ArrayList<>();

        do {

            choice = view.getUserChoice(choices);
            if (choice == 0) {
                return;
            }

            else if (choice == 1) {
                placeOrderController.addItemToOrder(item);
                return;
            }

            else if (choice == 2) {
                CustomOptionsBean bean = new CustomOptionsBean(chooseToppings,shots,caff,cal);
                item = placeOrderController.customizeMenuItem(bean);
                view.drawItemInfo(item);
                view.showChoices(choices);

                caff = 0;
                cal = 0;
                shots = 0;
                chooseToppings.clear();
            }

            else if (choice == 3) {
                System.out.println("insert your weight: ");
                caff = Double.parseDouble(view.getString());
                view.showChoices(choices);
            }

            else if (choice == 4) {
                System.out.println("insert your weight: ");
                cal = Double.parseDouble(view.getString());
                view.showChoices(choices);
            }

            else if (choice == 5) {
                System.out.println("insert additional coffee shots: ");
                shots = Integer.parseInt(view.getString());
                view.showChoices(choices);
            }

            else if (choice == 6) {
                view.drawShowToppings(toppings);
                chooseToppings.add(toppings.get(view.getUserChoice(toppingsName)).getName());
                view.showChoices(choices);
            }

        }while(choice != 1);

    }
}
