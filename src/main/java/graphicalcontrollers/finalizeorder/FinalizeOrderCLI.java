package graphicalcontrollers.finalizeorder;

import bean.OrderDetailBean;
import controller.PlaceOrderController;
import exception.WrongFormatException;
import graphicalcontrollers.orderbuilder.OrderBuilderCLI;
import graphicalcontrollers.ordersummary.OrderSummaryCLI;
import viewcli.FinalizeOrderViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalizeOrderCLI {

    private PlaceOrderController contrAppl;
    private FinalizeOrderViewCli view;

    public void launch(PlaceOrderController contrAppl) {
        this.contrAppl = contrAppl;
        view = new FinalizeOrderViewCli();
        view.drawTitle();
        int choice;
        int i=0;


        do {
            OrderDetailBean bean = view.drawGetInfo();
            List<String> choices = new ArrayList<>(Arrays.asList("continue order", "change details", "go back"));
            view.showChoices(choices);
            choice = view.getUserChoice(choices);

            switch (choice) {
                case 0:
                    try {
                        contrAppl.buildOrder(bean);
                    } catch (WrongFormatException e) {
                        break;
                    }
                    new OrderSummaryCLI().launch(contrAppl);
                    i = -1;
                    break;

                case 1:
                    break;

                case 2:
                    new OrderBuilderCLI().launch(contrAppl);
                    i = -1;
                    break;
            }


        }while(i != -1);

    }
}
