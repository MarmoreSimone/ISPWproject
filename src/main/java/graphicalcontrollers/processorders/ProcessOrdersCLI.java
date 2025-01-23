package graphicalcontrollers.processorders;

import bean.OrderRequestBean;
import controller.ProcessOrdersController;

import exception.SystemErrorException;
import viewcli.ProcessOrdersViewCli;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessOrdersCLI {

    public void launch(){

        ProcessOrdersController controller = new ProcessOrdersController();
        ProcessOrdersViewCli view = new ProcessOrdersViewCli();
        int choice;
        int choice2;

        view.drawTitle();

            do {

                List<OrderRequestBean> request = controller.getAllRequest();
                choice = view.drawOrders(request);
                List<String> choices = new ArrayList<>(Arrays.asList("accept", "reject", "reject with reason"));
                view.showChoices(choices);
                choice2 = view.getUserChoice(choices);

                switch (choice2) {
                    case 0:
                        try {
                            controller.acceptRequest(request.get(choice));
                        } catch (SystemErrorException e) {
                            e.showException();
                        }
                        break;

                    case 1:
                        try {
                            controller.rejectRequest(request.get(choice), null);
                        } catch (SystemErrorException e) {
                            e.showException();
                        }
                        break;

                    case 2:
                        try {
                            controller.rejectRequest(request.get(choice), view.getString());
                        } catch (SystemErrorException e) {
                            e.showException();
                        }
                        break;

                    default:
                        break;
                }

            } while (true);

    }


}
