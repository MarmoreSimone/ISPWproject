package starter;

import graphicalcontrollers.DummyGC;
import graphicalcontrollers.login.LoginGUI;
import graphicalcontrollers.myorders.MyOrdersGUI;
import graphicalcontrollers.processorders.ProcessOrderGUI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaGUI;
import javafx.fxml.FXML;

public class MenuController {


    @FXML
    private void onButtonClickPlaceOrder() {
       new SearchCafeteriaGUI().launch();
    }

    @FXML
    private void clickLogout() {
        new LoginGUI().launch();
    }

    @FXML
    private void onButtonClickDummy() {
        new DummyGC().launch();
    }

    @FXML
    private void onButtonClickGift() {
        new ProcessOrderGUI().launch();
    }

    @FXML
    private void onButtonClickMyOrder() {
            new MyOrdersGUI().launch();
    }






}