package graphicalcontrollers.ordersummary;

import bean.CafeteriaBean;
import bean.OrderBean;
import controller.PlaceOrderController;
import controller.SearchCafeteriaController;
import graphicalcontrollers.finalizeorder.FinalizeOrderCLI;
import graphicalcontrollers.home.HomeClientCLI;
import viewcli.OrderSummaryViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderSummaryCLI {

    private PlaceOrderController contrAppl;


    public void launch(PlaceOrderController contrAppl) {
        this.contrAppl = contrAppl;
        OrderSummaryViewCli view = new OrderSummaryViewCli();
        view.drawTitle();

        OrderBean order = contrAppl.getMyOrder();
        SearchCafeteriaController search = new SearchCafeteriaController();
        CafeteriaBean cafe = search.getCafeBeanByName(contrAppl.getCafeteriaName());

        view.drawOrderSummary(order,cafe);

        List<String> choices = new ArrayList<>(Arrays.asList("save order","go back"));
        view.showChoices(choices);
        int choice = view.getUserChoice(choices);

        if(choice == 0) {
            contrAppl.sendOrderRequest();
            new HomeClientCLI().launch();
        }
        else {
            new FinalizeOrderCLI().launch(contrAppl);
        }

    }
}
