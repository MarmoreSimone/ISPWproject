package graphicalcontrollers.ordersummary;

import bean.CafeteriaBean;
import bean.OrderBean;
import controller.PlaceOrderController;
import controller.SearchCafeteriaController;
import engineering.ControllerSessionManager;
import exception.NoCafeteriasFoundException;
import graphicalcontrollers.finalizeorder.FinalizeOrderCLI;
import graphicalcontrollers.home.HomeClientCLI;
import viewcli.OrderSummaryViewCli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderSummaryCLI {

    private PlaceOrderController contrAppl;


    public void launch(String session) {
        this.contrAppl = new PlaceOrderController(session);
        OrderSummaryViewCli view = new OrderSummaryViewCli();
        view.drawTitle();

        OrderBean order = contrAppl.getMyOrder();
        SearchCafeteriaController search = new SearchCafeteriaController();
        CafeteriaBean cafe = null;
        try {
            cafe = search.getCafeBeanByName(contrAppl.getCafeteriaName());
        } catch (NoCafeteriasFoundException e) {
            e.showException();
        }

        view.drawOrderSummary(order,cafe);

        List<String> choices = new ArrayList<>(Arrays.asList("save order","go back"));
        view.showChoices(choices);
        int choice = view.getUserChoice(choices);

        if(choice == 0) {
            contrAppl.sendOrderRequest();
            ControllerSessionManager.getInstance().delPlaceOrderSession(session);
            new HomeClientCLI().launch();
        }
        else {
            new FinalizeOrderCLI().launch(session);
        }

    }
}
