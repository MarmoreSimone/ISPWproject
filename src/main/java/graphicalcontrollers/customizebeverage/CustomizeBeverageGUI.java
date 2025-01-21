package graphicalcontrollers.customizebeverage;

import bean.CustomOptionsBean;
import bean.MenuItemBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utils.SwitchPage;

import java.util.ArrayList;
import java.util.List;

public class CustomizeBeverageGUI extends GraphicalController {

    @FXML
    private Label caffeine;

    @FXML
    private ListView<String> toppList;

    @FXML
    private Label calories;

    @FXML
    private Text description;

    @FXML
    private ImageView photo;

    @FXML
    private Label price;

    @FXML
    private Label name;

    @FXML
    private TextField shots;

    @FXML
    private RadioButton caffInfo;

    @FXML
    private RadioButton calInfo;

    @FXML
    private TextField weight;

    private PlaceOrderController controllerAppl;

    private MenuItemBean menuItem;

    private String session;

    private List<MenuItemBean> toppings;

    public void launch(String session) {
        SwitchPage.getSwitchPageInstance().changePage("/view/customizeBevGUI.fxml", session);
    }

    @Override
    public void setSession(String session){
        controllerAppl = new PlaceOrderController(session);
        this.session = session;
    }



    @Override
    public void initialize2() {
        this.menuItem = controllerAppl.getCustomBev();
        toppings = controllerAppl.retrieveToppings();
        List<String> itemsInListView = new ArrayList<>();

        for (MenuItemBean item : toppings) {
            itemsInListView.add(item.getName() + " (" +item.getPrice() + "$)");
        }

        toppList.getItems().setAll(itemsInListView);
        // Permetti la selezione multipla
        toppList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        setItemInfo(this.menuItem);
    }

    public void setItemInfo(MenuItemBean item) {
        name.setText(menuItem.getName());
        caffeine.setText(String.valueOf(menuItem.getCaffeine()) + "mg");
        calories.setText(String.valueOf(menuItem.getCalories())+ "Kcal");
        description.setText(menuItem.getDescription());
        price.setText(String.valueOf(menuItem.getPrice()) + "$");
        photo.setImage(new Image(getClass().getResourceAsStream(menuItem.getImage())));
    }

    public void goBack() {
        new OrderBuilderGUI().launch(session);
    }

    public void customize(){

        double caff=0;
        double cal=0;

        if(weight.getText().isEmpty()){
            weight.setText("70");
        }

        if(shots.getText().isEmpty()){
            shots.setText("0");
        }

        if(caffInfo.isSelected()){
            caff = Double.parseDouble(weight.getText());
        }

        if(calInfo.isSelected()){
            cal = Double.parseDouble(weight.getText());
        }

        List<Integer> selectedTopp = toppList.getSelectionModel().getSelectedIndices();
        List<String> chooseTopp = new ArrayList<>();

        for(int i=0; i<selectedTopp.size(); i++){
            chooseTopp.add(this.toppings.get(selectedTopp.get(i)).getName());
        }

        CustomOptionsBean bean = new CustomOptionsBean(chooseTopp,Integer.parseInt(shots.getText()),caff,cal);
        this.menuItem = controllerAppl.customizeMenuItem(bean);
        setItemInfo(this.menuItem);
        toppList.getSelectionModel().clearSelection();

    }

    public void addToOrder(){
        controllerAppl.addItemToOrder(this.menuItem);
        goBack();
    }

}
