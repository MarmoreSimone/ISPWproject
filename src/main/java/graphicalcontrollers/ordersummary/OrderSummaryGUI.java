package graphicalcontrollers.ordersummary;

import bean.CafeteriaBean;
import bean.OrderBean;
import controller.PlaceOrderController;
import exception.SystemErrorException;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import utils.SwitchPage;


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
    private ListView list;


    private String session;

    public void launch(String session) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderSummary.fxml", session);
    }

    @Override
    public void setSession(String session){
        controllerAppl = new PlaceOrderController(session);
        this.session = session;
    }

    @Override
    public void initialize2() {

        OrderBean order = controllerAppl.getMyOrder();
        CafeteriaBean cafe = controllerAppl.getSettedCafeteria();

        address.setText(cafe.getAddress());
        city.setText(cafe.getCity());
        date.setText(order.getDate());
        nameCafe.setText(cafe.getName());
        note.setText(order.getNote());
        tot.setText(String.valueOf(order.getTotPrice()) + "$");
        time.setText(String.valueOf(order.getTime()));
        payMet.setText(order.getPayMethod());


        for(int i=0;i<order.getBevs().size();i++){

            String[] parts = order.getBevs().get(i).getName().split("\n", 2);
            String firstWord = parts[0];
            String rest = parts.length > 1 ? parts[1] : "";

            list.getItems().add(firstWord + " " + order.getBevs().get(i).getPrice() + "$\n" + rest);

        }

    }

    public void goBackToFinalize() {
        new FinalizeOrderGUI().launch(this.session);
    }

    public void confirmOrder(){
        try {

            controllerAppl.sendOrderRequest(this.session);
            SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml");

        }catch(SystemErrorException e){
            e.showException();
        }

    }


}
