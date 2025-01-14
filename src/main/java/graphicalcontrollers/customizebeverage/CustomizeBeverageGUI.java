package graphicalcontrollers.customizebeverage;

import bean.BeverageBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utils.SwitchPage;

public class CustomizeBeverageGUI extends GraphicalController {

    @FXML
    private Label caffeine;

    @FXML
    private Label calories;

    @FXML
    private Text description;

    @FXML
    private Button goBack;

    @FXML
    private ImageView photo;

    @FXML
    private Label price;

    @FXML
    private Label name;

    private PlaceOrderController controllerAppl;

    private BeverageBean beverage;

    private String session;

    public void launch(String session) {
        SwitchPage.getSwitchPageInstance().changePage("/view/customizeBevGUI.fxml", session);
    }

    public void setSession(String session){
        controllerAppl = new PlaceOrderController(session);
        this.session = session;
    }


    @Override
    public void setBeverage(BeverageBean beverage) {
        this.beverage = beverage;
    }


    @Override
    public void initialize2() {
        setBeverage(controllerAppl.getCustomBev());
        name.setText(beverage.getName());
        caffeine.setText(String.valueOf(beverage.getCaffeine()) + "mg");
        calories.setText(String.valueOf(beverage.getCalories())+ "Kcal");
        description.setText(beverage.getDescription());
        price.setText(String.valueOf(beverage.getPrice()) + "$");
        photo.setImage(new Image(getClass().getResourceAsStream(beverage.getImage())));
    }

    public void goBack() {
        new OrderBuilderGUI().launch(session);
    }



}
