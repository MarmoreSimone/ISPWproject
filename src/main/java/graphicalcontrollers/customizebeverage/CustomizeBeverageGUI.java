package graphicalcontrollers.customizebeverage;

import bean.BeverageBean;
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

    private OrderBuilderGUI parentContr;

    private BeverageBean beverage;

    public void launch(OrderBuilderGUI controller , BeverageBean beverage) {
        SwitchPage.getSwitchPageInstance().changePage("/view/customizeBevGUI.fxml", controller, beverage );
    }

    @Override
    public void setContrOrderBuilder(OrderBuilderGUI controller) {
        parentContr = controller;
    }

    @Override
    public void setBeverage(BeverageBean beverage) {
        this.beverage = beverage;
    }

    @FXML
    private Button addToOrder;

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

    @Override
    public void initialize2() {
        name.setText(beverage.getName());
        caffeine.setText(String.valueOf(beverage.getCaffeine()) + "mg");
        calories.setText(String.valueOf(beverage.getCalories())+ "Kcal");
        description.setText(beverage.getDescription());
        price.setText(String.valueOf(beverage.getPrice()) + "$");
        photo.setImage(new Image(getClass().getResourceAsStream(beverage.getImage())));
    }

    public void goBack() {
        new OrderBuilderGUI().launch(parentContr.getContrAppl());
    }



}
