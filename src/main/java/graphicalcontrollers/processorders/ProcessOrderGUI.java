package graphicalcontrollers.processorders;

import bean.OrderBean;
import controller.OrdersRequestController;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utils.SwitchPage;

import java.util.List;

public class ProcessOrderGUI extends GraphicalController {


    @FXML
    private VBox requestList;


    private OrdersRequestController contrAppl;
            ;
    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/processOrders.fxml");
    }

    @Override
    public void initialize2(){
        this.contrAppl = new OrdersRequestController();
        showRequest();


        }

    public void showRequest(){
        List<OrderBean> orders = contrAppl.getAllRequest();
        List<Object> objectList = (List<Object>) (List<?>) orders;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/evaluateOrder.fxml",requestList,this,objectList);
    }

    public void accept(OrderBean bean){
        contrAppl.acceptRequest(bean);
        showRequest();
    }

    public void reject(OrderBean bean){
        contrAppl.rejectRequest(bean);
        showRequest();
    }


}
