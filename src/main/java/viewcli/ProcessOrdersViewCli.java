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
                String[] parts = order.getOrder().getBevs().get(i).getName().split("\n", 2);
                String firstWord = parts[0];
                String rest = parts.length > 1 ? parts[1] : "";
                string = string.concat(firstWord + " " + order.getOrder().getBevs().get(i).getPrice() + "$\n" + rest);
            }

            string = string.concat("\n----------------------");

            list.add(string);
        }


        showChoices(list);

        return getUserChoice(list);


    }


}
