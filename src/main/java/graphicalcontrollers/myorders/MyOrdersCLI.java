package graphicalcontrollers.myorders;


import bean.OrderRequestBean;
import controller.UserOrdersController;
import graphicalcontrollers.home.HomeClientCLI;
import viewcli.MyOrdersViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyOrdersCLI {

    public void launch(){

        MyOrdersViewCli view = new MyOrdersViewCli();
        UserOrdersController controller = new UserOrdersController();
        List<OrderRequestBean> list;

        view.drawTitle();
        int choice;
        List<String> choices = new ArrayList<>(Arrays.asList("get all", "accepted", "rejected", "pending", "exit"));

        list = controller.getAllMyOrderReq();
        int i = 0;
        do {
            view.showChoices(choices);
            view.drawOrders(list);
            choice = view.getUserChoice(choices);

                switch (choice) {
                    case 0:
                        list = controller.getAllMyOrderReq();
                        break;

                    case 1:
                        list = controller.getAllMyOrderReq("ACCEPTED");
                        break;

                    case 2:
                        list = controller.getAllMyOrderReq("REJECTED");
                        break;

                    case 3:
                        list = controller.getAllMyOrderReq("PENDING");
                        break;

                    case 4:
                        new HomeClientCLI().launch();
                        i = 1;
                        break;

                    default:
                        break;
                }

        } while (i != 1);

    }
}
