package graphicalcontrollers.orderbuilder;

import bean.BeverageBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;

import graphicalcontrollers.customizebeverage.CustomizeBeverageGUI;
import graphicalcontrollers.finalizeorder.FinalizeOrderGUI;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import utils.SwitchPage;


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
    public void launch(SearchCafeteriaBean bean) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", bean);
    }

    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", controller);
    }


    @Override
    //usato la prima volta che parte il controllore grafico
    public void setCafeteria(SearchCafeteriaBean bean) {
        this.controllerAppl = new PlaceOrderController();
        this.controllerAppl.setCafeteria(bean);
    }

    @Override
    //usato quando dalla customization/info devo tornare indietro
    public void setControllerApplPlaceOrder(PlaceOrderController controller){
        this.controllerAppl = controller;
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

        List<Object> objectList = (List<Object>) (List<?>) beverages;
        SwitchPage.getSwitchPageInstance().changeMiniPage("/view/cell/menuItem.fxml",beverageList,this,objectList);


    }

    public void showAddedBev(){
        List<BeverageBean> addedBev = controllerAppl.getAddedBev();

        //casto la lista di bevande ad una lista generica e dopo nel mini-controllore grafico la ricasto a Beverage Bean
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
        new CustomizeBeverageGUI().launch(this,beverage);

    }

    public void continueOrder(){
        new FinalizeOrderGUI().launch(controllerAppl);
    }

    }


