package graphicalcontrollers.ordersummary;

import bean.OrderBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.order.Order;
import utils.SwitchPage;
import javafx.fxml.FXML;
public class OrderSummaryGUI extends GraphicalController {

    private PlaceOrderController controllerAppl;

    @FXML
    private Label address;

    @FXML
    private Label city;

    @FXML
    private Label date;


    @FXML
    private Label nameCafe;

    @FXML
    private Text note;

    @FXML
    private Text payMet;

    @FXML
    private Label time;

    @FXML
    private Label tot;

    @FXML
    private ListView<String> itemList;

    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderSummary.fxml", controller);
    }

    @Override
    public void setControllerApplPlaceOrder(PlaceOrderController controllerAppl) {
        this.controllerAppl = controllerAppl;
    }

    @Override
    public void initialize2() {

        OrderBean order = controllerAppl.getOrder();
        address.setText(order.getCafeteria().getAddress());
        city.setText(order.getCafeteria().getCity());
        date.setText(order.getDate());
        nameCafe.setText(order.getCafeteria().getName());
        note.setText(order.getNote());
        tot.setText(String.valueOf(order.getTotPrice()) + "$");
        time.setText(String.valueOf(order.getTime()));
        payMet.setText(order.getPayMethod());

        for(int i=0;i<order.getBevs().size();i++){
            itemList.getItems().add(order.getBevs().get(i).getName() + "  " + order.getBevs().get(i).getPrice() + "$");
        }

    }

    public void goBackToFinalize() {
        new FinalizeOrderGUI().launch(controllerAppl);
    }

    public void confirmOrder(){
        controllerAppl.sendOrder();
        SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml");
    }


}