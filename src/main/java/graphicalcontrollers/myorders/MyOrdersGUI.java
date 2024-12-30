package graphicalcontrollers.myorders;

import bean.OrderRequestBean;
import controller.PlaceOrderController;
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
    private RadioButton Raccept;

    @FXML
    private RadioButton Rall;

    @FXML
    private RadioButton Rpending;

    @FXML
    private RadioButton Rreject;

    @FXML
    private BorderPane mainBorderPane;

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/myOrdersGUI.fxml");
    }

    @Override
    public void initialize2(){

        PlaceOrderController placeOrderController = new PlaceOrderController();
        List<OrderRequestBean> ordini = placeOrderController.getAllMyOrderReq();
        loadMyOrders(ordini,"all");

        ToggleGroup tg = new ToggleGroup();

        Raccept.setToggleGroup(tg);
        Rall.setToggleGroup(tg);
        Rpending.setToggleGroup(tg);
        Rreject.setToggleGroup(tg);

        Rall.setSelected(true);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    if(selectedRadioButton == Raccept){
                        loadMyOrders(ordini,"ACCEPTED");
                    }
                    else if(selectedRadioButton == Rall){
                        loadMyOrders(ordini,"all");
                    }

                    else if(selectedRadioButton == Rpending){
                        loadMyOrders(ordini,"PENDING");
                    }

                    else if(selectedRadioButton == Rreject){
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
