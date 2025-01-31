package graphicalcontrollers.myorders;

import bean.OrderRequestBean;
import controller.UserOrdersController;
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
    UserOrdersController contr;

    @Override
    public void initialize2(){

        contr = new UserOrdersController();

        ordini = contr.getAllMyOrderReq();

        loadMyOrders("all");

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
                        loadMyOrders("ACCEPTED");
                    }
                    else if(selectedRadioButton == all){
                        loadMyOrders("all");
                    }

                    else if(selectedRadioButton == pending){
                        loadMyOrders("PENDING");
                    }

                    else if(selectedRadioButton == reject){
                        loadMyOrders("REJECTED");
                    }
                }
            }
        });
    }

    public void loadMyOrders(String choice){


        if(choice.equals("all")) ordini = contr.getAllMyOrderReq();
        else ordini = contr.getAllMyOrderReq(choice);

        List<Object> objectList = (List<Object>) (List<?>) ordini;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/myOrderCell.fxml",ordList,this,objectList);

    }


}
