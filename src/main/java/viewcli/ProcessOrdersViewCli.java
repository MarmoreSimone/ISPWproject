package viewcli;

import bean.OrderRequestBean;

import java.util.ArrayList;
import java.util.List;

public class ProcessOrdersViewCli extends viewcli.UtilsCli{

    public void drawTitle(){
        System.out.println("===PROCESS ORDERS===");
    }

    public int drawOrders(List<OrderRequestBean> orders){

        List<String> list = new ArrayList<String>();
        System.out.println("chose an order: (0 to exit)");

        for(OrderRequestBean order : orders){

            String string = order.getUsername() + "\n" + order.getOrder().getDate() + " " + order.getOrder().getTime() + "\n" + "note:\n" + order.getOrder().getNote()+"\n";

            for (int i = 0; i < order.getOrder().getBevs().size(); i++) {
                string = string.concat(order.getOrder().getBevs().get(i).getName() + "  " + order.getOrder().getBevs().get(i).getPrice() + "$\n");
            }

            string = string.concat("----------------------");

            list.add(string);
        }


        showChoices(list);

        return getUserChoice(list);


    }


}
