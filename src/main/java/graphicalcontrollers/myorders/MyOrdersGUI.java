package graphicalcontrollers.myorders;

import bean.OrderRequestBean;
import controller.PlaceOrderController;
import controller.UserOrdersController;
import exception.SystemErrorException;
import graphicalcontrollers.GraphicalController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.SwitchPage;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersGUI extends GraphicalController {

    @FXML
    private VBox ordList;

    @FXML
    private RadioButton accept;

    @FXML
    private RadioButton all;

    @FXML
    private RadioButton pending;

    @FXML
    private RadioButton reject;

    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/myOrdersGUI.fxml");
    }

    List<OrderRequestBean> ordini;

    @Override
    public void initialize2(){

        UserOrdersController controller = new UserOrdersController();


        try {
            ordini = controller.getAllMyOrderReq();
        } catch (SystemErrorException e) {
            e.showException();
        }

        loadMyOrders(ordini,"all");

        ToggleGroup tg = new ToggleGroup();

        accept.setToggleGroup(tg);
        all.setToggleGroup(tg);
        pending.setToggleGroup(tg);
        reject.setToggleGroup(tg);

        all.setSelected(true);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    if(selectedRadioButton == accept){
                        loadMyOrders(ordini,"ACCEPTED");
                    }
                    else if(selectedRadioButton == all){
                        loadMyOrders(ordini,"all");
                    }

                    else if(selectedRadioButton == pending){
                        loadMyOrders(ordini,"PENDING");
                    }

                    else if(selectedRadioButton == reject){
                        loadMyOrders(ordini,"REJECTED");

                    }


                }
            }
        });
    }

    public void loadMyOrders(List<OrderRequestBean> orders, String choice){

        List<OrderRequestBean> ord = new ArrayList<OrderRequestBean>();

        if(choice.equals("all")){
            ord = orders;
        }
        else {
            for (OrderRequestBean order : orders) {
                //evito problemi visto che la response la attacco direttamente alla stringa rejected
                if (order.getState().charAt(0) == choice.charAt(0)) {
                    ord.add(order);
                }

            }
        }

        List<Object> objectList = (List<Object>) (List<?>) ord;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/myOrderCell.fxml",ordList,this,objectList);
    }


}
