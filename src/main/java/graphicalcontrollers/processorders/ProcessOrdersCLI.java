package graphicalcontrollers.processorders;

import bean.OrderRequestBean;
import controller.OrdersRequestController;
import viewcli.ProcessOrdersViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessOrdersCLI {

    public void launch(){

        OrdersRequestController controller = new OrdersRequestController();
        ProcessOrdersViewCli view = new ProcessOrdersViewCli();
        int choice, choice2;


        view.drawTitle();

        do {

            List<OrderRequestBean> request = controller.getAllRequest();
            choice = view.drawOrders(request);
            List<String> choices = new ArrayList<>(Arrays.asList("accept", "reject", "reject with reason"));
            view.showChoices(choices);
            choice2 = view.getUserChoice(choices);

            switch (choice2) {
                case 0:
                    controller.acceptRequest(request.get(choice));
                    break;

                case 1:
                    controller.rejectRequest(request.get(choice), null);
                    break;

                case 2:
                    controller.rejectRequest(request.get(choice), view.getString());
                    break;
            }

        }while(true);




    }


}
