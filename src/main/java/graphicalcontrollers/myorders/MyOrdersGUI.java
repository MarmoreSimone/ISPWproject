package graphicalcontrollers.myorders;

import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import model.DAOfactory;
import model.order.Order;
import utils.SwitchPage;

import java.util.List;

public class MyOrdersGUI extends GraphicalController {

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/myOrdersGUI.fxml");
    }

    @Override
    public void initialize2(){
        List<Order> ordini = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();
        for (Order order : ordini) {
            System.out.println(order.getCafeteria().getName() + "  " +order.getTotPrice()+"$  " + order.getStatus());
        }

    }


}
