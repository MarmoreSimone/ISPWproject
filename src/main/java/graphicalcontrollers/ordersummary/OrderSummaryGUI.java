package graphicalcontrollers.ordersummary;

import bean.CafeteriaBean;
import bean.OrderBean;
import controller.PlaceOrderController;
import controller.SearchCafeteriaController;
import engineering.ControllerSessionManager;
import exception.NoCafeteriasFoundException;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private VBox vBox;

    @FXML
    private ListView<String> itemList;

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
        SearchCafeteriaController search = new SearchCafeteriaController();

        CafeteriaBean cafe = null;
        try {
            cafe = search.getCafeBeanByName(controllerAppl.getCafeteriaName());
        } catch (NoCafeteriasFoundException e) {
            e.showException();
        }

        address.setText(cafe.getAddress());
        city.setText(cafe.getCity());
        date.setText(order.getDate());
        nameCafe.setText(cafe.getName());
        note.setText(order.getNote());
        tot.setText(String.valueOf(order.getTotPrice()) + "$");
        time.setText(String.valueOf(order.getTime()));
        payMet.setText(order.getPayMethod());

        /*
        for(int i=0;i<order.getBevs().size();i++){
            itemList.getItems().add(order.getBevs().get(i).getName() + "  " + order.getBevs().get(i).getPrice() + "$");
        }

         */
        for(int i=0;i<order.getBevs().size();i++){
            Label nameLabel = new Label(order.getBevs().get(i).getName());
            nameLabel.setStyle("-fx-font-size: 18px;");


            // Creazione dell'HBox per nome e personalizzazioni
            HBox hBox = new HBox(nameLabel);
            hBox.setSpacing(2);

            // Creazione della Label per il prezzo
            Label priceLabel = new Label(order.getBevs().get(i).getPrice() + "$");
            priceLabel.setStyle("-fx-font-size: 18px;");

            // Aggiungi il prezzo all'HBox
            HBox hBoxFinal = new HBox(hBox, priceLabel);
            hBoxFinal.setSpacing(10);

            HBox.setMargin(nameLabel, new Insets(0, 10, 0, 10));
            // Aggiungi l'HBox alla VBox
            vBox.getChildren().add(hBoxFinal);
        }

    }

    public void goBackToFinalize() {
        new FinalizeOrderGUI().launch(this.session);
    }

    public void confirmOrder(){
        controllerAppl.sendOrderRequest();
        ControllerSessionManager.getInstance().delPlaceOrderSession(this.session);
        SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml");
    }


}
