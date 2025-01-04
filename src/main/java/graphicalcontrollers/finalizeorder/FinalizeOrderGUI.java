package graphicalcontrollers.finalizeorder;

import bean.OrderDetailBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import graphicalcontrollers.ordersummary.OrderSummaryGUI;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.SwitchPage;

public class FinalizeOrderGUI extends GraphicalController {

    private PlaceOrderController controllerAppl;

    @FXML
    private DatePicker day;

    @FXML
    private TextArea note;

    @FXML
    private TextField time;

    @FXML
    private RadioButton payNow;

    @FXML
    private RadioButton payLater;

    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/finalizeOrder.fxml", controller);
    }

    @Override
    public void setControllerApplPlaceOrder(PlaceOrderController controllerAppl) {
        this.controllerAppl = controllerAppl;
    }

    @Override
    public void initialize2() {

        ToggleGroup tg = new ToggleGroup();
        payNow.setToggleGroup(tg);
        payLater.setToggleGroup(tg);

    }

    public void goBackToBuild(){
        new OrderBuilderGUI().launch(controllerAppl);
    }

    public void goToSummary(){
        String payMeth;
        if(payNow.isSelected()) payMeth = payNow.getText();

        else payMeth = payLater.getText();

        controllerAppl.buildOrder(new OrderDetailBean(payMeth,note.getText(),day.getValue().toString(), time.getText() ));
        new OrderSummaryGUI().launch(controllerAppl);

    }




}
