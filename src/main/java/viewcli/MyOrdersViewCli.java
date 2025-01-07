package viewcli;

import bean.OrderRequestBean;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersViewCli extends viewcli.UtilsCli{

    public void drawTitle(){
        System.out.println("===MY ORDERS===");
    }

    public void drawOrders(List<OrderRequestBean> orders){

        List<String> list = new ArrayList<String>();

        for(OrderRequestBean order : orders){

            String string = "*****************************\n" + "cafeteria: ".toUpperCase() + order.getCafe();
            string = string.concat("\ndate: ".toUpperCase() + order.getOrder().getDate() + " " + order.getOrder().getTime() + "\npickup code: ".toUpperCase() + order.getCode() + "\nstatus: ".toUpperCase() + order.getState() + "\n");

            string = string.concat("\n");
            for (int i = 0; i < order.getOrder().getBevs().size(); i++) {
                string = string.concat(order.getOrder().getBevs().get(i).getName() + "  " + order.getOrder().getBevs().get(i).getPrice() + "$\n");
            }
            string = string.concat("\n");
            string = string.concat("Total: ".toUpperCase()  + order.getOrder().getTotPrice()+"$");


            list.add(string);
        }


        showList(list);


    }
}
