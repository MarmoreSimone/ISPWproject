package viewcli;

import bean.OrderDetailBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalizeOrderViewCli extends viewcli.UtilsCli{

    public void drawTitle(){
        System.out.println("==FINALIZE ORDER==");

    }

    public OrderDetailBean drawGetInfo(){
        OrderDetailBean bean = new OrderDetailBean();

        System.out.println("pick up date: (YYYY-MM-DD)");
        bean.setDate(getString());
        System.out.println("pick up time: (18:00)");
        bean.setTime(getString());
        System.out.println("message for the barista: ");
        bean.setNote(getString());
        List<String> choices = new ArrayList<>(Arrays.asList("pay now", "pay at pickup"));
        showChoices(choices);
        int choice = getUserChoice(choices);
        if(choice == 0){
            bean.setPayMethod("pay now");
        }
        else{
            bean.setPayMethod("pay at pickup");
        }

        return bean;

    }


}
