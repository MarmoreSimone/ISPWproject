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

            String string =
            order.getCafe() +"\n"+order.getOrder().getDate() + " " + order.getOrder().getTime() +
                     "\npickup code: " + order.getCode() + "\nstatus: "+order.getState()+"\n";

            list.add(string);
        }


        showList(list);


    }
}
