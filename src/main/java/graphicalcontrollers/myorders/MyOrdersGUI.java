package graphicalcontrollers.myorders;

import bean.OrderBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utils.SwitchPage;

import java.util.List;

public class MyOrdersGUI extends GraphicalController {

    @FXML
    private VBox ordList;

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/myOrdersGUI.fxml");
    }

    @Override
    public void initialize2(){
        PlaceOrderController placeOrderController = new PlaceOrderController();
        List<OrderBean> ordini = placeOrderController.getAllOrders();
        loadMyOrders(ordini);

    }

    public void loadMyOrders(List<OrderBean> orders){
        List<Object> objectList = (List<Object>) (List<?>) orders;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/myOrderCell.fxml",ordList,this,objectList);


    }


}
