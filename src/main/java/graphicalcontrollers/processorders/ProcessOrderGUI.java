package graphicalcontrollers.processorders;


import bean.OrderRequestBean;
import controller.ProcessOrdersController;
import exception.SystemErrorException;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utils.SwitchPage;

import java.util.List;

public class ProcessOrderGUI extends GraphicalController {


    @FXML
    private VBox requestList;


    private ProcessOrdersController contrAppl;

    @Override
    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/processOrders.fxml");
    }

    @Override
    public void initialize2(){
        this.contrAppl = new ProcessOrdersController();
        showRequest();


        }

    public void showRequest(){
        List<Object> objectList;
        List<OrderRequestBean> orders = null;

        orders = contrAppl.getAllRequest();

        objectList = (List<Object>) (List<?>) orders;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/evaluateOrder.fxml",requestList,this,objectList);
    }

    public void accept(OrderRequestBean bean){
        contrAppl.acceptRequest(bean);
        showRequest();
    }

    public void reject(OrderRequestBean bean, String reason){
        contrAppl.rejectRequest(bean,reason);
        showRequest();
    }


}
