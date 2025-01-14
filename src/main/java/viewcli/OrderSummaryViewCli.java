package viewcli;

import bean.CafeteriaBean;
import bean.OrderBean;

public class OrderSummaryViewCli extends viewcli.UtilsCli{

    public void drawTitle(){
        System.out.println("===ORDER SUMMARY===");

    }

    public void drawOrderSummary(OrderBean order, CafeteriaBean cafe){
        System.out.println("Cafeteria: " +cafe.getName());
        System.out.println("City: " +cafe.getCity());
        System.out.println("Address: " +cafe.getAddress());
        System.out.println("");
        System.out.println("Date: " + order.getDate());
        System.out.println("Time: " + order.getTime());
        System.out.println("Note: " + order.getNote());
        System.out.println("Payment method: " + order.getPayMethod());
        System.out.println("");
        System.out.println("Total: " + order.getTotPrice() + "$");
        System.out.println("");

        for(int i=0;i<order.getBevs().size();i++){
            System.out.println(order.getBevs().get(i).getName() + "  " + order.getBevs().get(i).getPrice() + "$");
        }
    }
}
