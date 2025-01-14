package graphicalcontrollers.finalizeorder;

import bean.OrderDetailBean;
import controller.PlaceOrderController;
import exception.WrongFormatException;
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

    private String session;

    public void launch(String session) {
        SwitchPage.getSwitchPageInstance().changePage("/view/finalizeOrder.fxml", session);
    }

    public void setSession(String session){
        controllerAppl = new PlaceOrderController(session);
        this.session = session;
    }


    public void initialize2() {

        ToggleGroup tg = new ToggleGroup();
        payNow.setToggleGroup(tg);
        payLater.setToggleGroup(tg);

    }

    public void goBackToBuild(){
        new OrderBuilderGUI().launch(this.session);
    }

    public void goToSummary(){

        String payMeth;

        if(payNow.isSelected()) payMeth = payNow.getText();
        else payMeth = payLater.getText();

        try {
            controllerAppl.buildOrder(new OrderDetailBean(payMeth,note.getText(),day.getValue().toString(), time.getText() ));
        }catch (WrongFormatException e){
            e.showException();
            return;
        }


        new OrderSummaryGUI().launch(this.session);

    }




}
