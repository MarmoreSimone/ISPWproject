package starter;

import engineering.ControllerSessionManager;
import graphicalcontrollers.DummyGC;
import graphicalcontrollers.login.LoginGUI;
import graphicalcontrollers.myorders.MyOrdersGUI;
import graphicalcontrollers.processorders.ProcessOrderGUI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaGUI;
import javafx.fxml.FXML;

public class MenuController {


    @FXML
    private void onButtonClickPlaceOrder() {
        ControllerSessionManager.getInstance().closeAllUserSessions();
        new SearchCafeteriaGUI().launch();
    }

    @FXML
    private void clickLogout() {
        ControllerSessionManager.getInstance().closeAllUserSessions();
        new LoginGUI().launch();
    }

    @FXML
    private void onButtonClickDummy() {
        new DummyGC().launch();
    }

    @FXML
    private void onButtonClickGift() {
        ControllerSessionManager.getInstance().closeAllUserSessions();
        new ProcessOrderGUI().launch();
    }

    @FXML
    private void onButtonClickMyOrder() {
        ControllerSessionManager.getInstance().closeAllUserSessions();
        new MyOrdersGUI().launch();
    }






}