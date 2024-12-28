package graphicalcontrollers.orderbuilder;

import bean.BeverageBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.cell.AddedBevCellContr;
import graphicalcontrollers.cell.MenuItemCellContr;
import graphicalcontrollers.customizebeverage.CustomizeBeverageGUI;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import starter.Main;
import utils.SwitchPage;

import java.io.IOException;
import java.util.List;

public class OrderBuilderGUI extends GraphicalController {

    @FXML
    private Text cafeName;

    @FXML
    private VBox beverageList;

    @FXML
    private VBox addedBevList;

    @FXML
    private Label totPrice;

    //istanza del controller applicativo
    private PlaceOrderController controllerAppl;
    //lista delle bevande della caffetteria
    private List<BeverageBean> beverages;

    //overload dell'operazione launch()
    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", controller);
    }

    @Override
    public void setControllerApplPlaceOrder(PlaceOrderController controllerAppl) {
        this.controllerAppl = controllerAppl;
    }

    @Override
    public void initialize2(){

        //imposto il nome della caffetteria
        cafeName.setText(controllerAppl.getCafeteriaName());
        //imposto le bevande della caffetteria
        beverages = controllerAppl.getCafeteriaBeverages();
        //funzione per mostrare a schermo le bevande aggiungibili all'ordine
        showBevMenu();
        showAddedBev();
        totPrice.setText(String.valueOf(getTot())+"$");
        }

    public PlaceOrderController getContrAppl(){
        return controllerAppl;
    }

    public void showBevMenu(){

        for(int i=0 ; i<beverages.size(); i++){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/cell/menuItem.fxml"));

            try{
                Pane pane = loader.load();
                MenuItemCellContr contr = loader.getController();
                contr.setData(beverages.get(i),this);
                beverageList.getChildren().add(pane);
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    public void showAddedBev(){
        List<BeverageBean> addedBev = controllerAppl.getAddedBev();
        addedBevList.getChildren().clear();

        for(int i=0 ; i<addedBev.size(); i++){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/cell/addedBev.fxml"));

            try{
                Pane pane = loader.load();
                AddedBevCellContr contr = loader.getController();
                contr.setData(addedBev.get(i),this);
                addedBevList.getChildren().add(pane);
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    public void addToOrder(BeverageBean beverage){
        controllerAppl.addBeverageToOrder(beverage);
        showAddedBev();
        totPrice.setText(String.valueOf(getTot())+"$");
    }

    public void removeFromOrder(BeverageBean beverage){
        controllerAppl.removeBeverageFromOrder(beverage);
        showAddedBev();
        totPrice.setText(String.valueOf(getTot())+"$");
    }

    public double getTot(){
        return controllerAppl.totalPrice();
    }

    public void customizeBev(BeverageBean beverage){
        new CustomizeBeverageGUI().launch(this,beverage);

    }

    public void continueOrder(){
        new FinalizeOrderGUI().launch(controllerAppl);
    }

    }


