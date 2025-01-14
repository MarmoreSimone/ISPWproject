package graphicalcontrollers.orderbuilder;

import bean.BeverageBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import engineering.ControllerSessionManager;
import exception.NoCafeteriasFoundException;
import graphicalcontrollers.GraphicalController;

import graphicalcontrollers.customizebeverage.CustomizeBeverageGUI;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import utils.SwitchPage;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderBuilderGUI extends GraphicalController {

    @FXML
    private Text cafeName;

    @FXML
    private VBox beverageList;

    @FXML
    private VBox addedBevList;

    @FXML
    private Label totPrice;

    private String session;


    private PlaceOrderController controllerAppl;
    //lista delle bevande della caffetteria
    private List<BeverageBean> beverages;

    public void launch(String session) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", session);
    }

    @Override
    public void setSession(String session){
        controllerAppl = new PlaceOrderController(session);
        this.session = session;
    }


    @Override
    public void initialize2() {
        //imposto il nome della caffetteria
        cafeName.setText(controllerAppl.getCafeteriaName());
        //imposto le bevande della caffetteria
        beverages = controllerAppl.getCafeteriaBeverages();
        //funzione per mostrare a schermo le bevande aggiungibili all'ordine
        showBevMenu();
        showAddedBev();
        totPrice.setText(String.valueOf(getTot())+"$");
    }


    public void showBevMenu(){

        List<Object> objectList = (List<Object>) (List<?>) beverages;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/menuItem.fxml",beverageList,this,objectList);

    }

    public void showAddedBev(){
        List<BeverageBean> addedBev = controllerAppl.getAddedBev();

        //casto la lista di bevande a una lista generica e dopo nel mini-controllore grafico la ricasto a Beverage Bean
        List<Object> objectList = (List<Object>) (List<?>) addedBev;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/addedBev.fxml",addedBevList,this,objectList);

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

        controllerAppl.setCustomBev(beverage);
        new CustomizeBeverageGUI().launch(this.session);

    }

    public void continueOrder(){
        new FinalizeOrderGUI().launch(this.session);
    }




}


