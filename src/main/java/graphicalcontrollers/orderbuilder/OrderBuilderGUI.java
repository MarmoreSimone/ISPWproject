package graphicalcontrollers.orderbuilder;

import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.cell.MenuItemCellContr;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.beverage.Beverage;
import starter.Main;
import utils.SwitchPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBuilderGUI extends GraphicalController {

    @FXML
    private Text cafeName;

    @FXML
    private VBox beverageList;

    private PlaceOrderController controllerAppl;

    //overload dell'operazione launch()
    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", controller);
    }

    @Override
    public void setControllerApplPlaceOrder(PlaceOrderController controllerAppl) {
        this.controllerAppl = controllerAppl;
    }

    public void initialize2(){
        cafeName.setText(controllerAppl.getCafeteriaName());
        showBevMenu(controllerAppl.getCafeteriaBeverages());
        }

    public void showBevMenu(List<Beverage> beverages){

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

    public void elimina(String nome){
        for(int i=0 ; i<beverageList.getChildren().size(); i++) {
            Pane pane = (Pane) beverageList.getChildren().get(i);
            Label temp = (Label) pane.getChildren().getFirst();

            if(temp.getText().equals(nome)) {
                beverageList.getChildren().remove(i);
            }
        }

    }



    }


