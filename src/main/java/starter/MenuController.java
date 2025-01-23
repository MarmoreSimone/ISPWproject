package starter;

import engineering.SessionManager;
import graphicalcontrollers.login.LoginGUI;
import graphicalcontrollers.myorders.MyOrdersGUI;
import graphicalcontrollers.processorders.ProcessOrderGUI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaGUI;
import javafx.fxml.FXML;

public class MenuController {


    @FXML
    private void onButtonClickPlaceOrder() {
        SessionManager.getInstance().closeAllUserSessions();
        new SearchCafeteriaGUI().launch();
    }

    @FXML
    private void clickLogout() {
        SessionManager.getInstance().closeAllUserSessions();
        new LoginGUI().launch();
    }

    @FXML
    private void onButtonClickDummy() {

    }

    @FXML
    private void onButtonClickGift() {
        SessionManager.getInstance().closeAllUserSessions();
        new ProcessOrderGUI().launch();
    }

    @FXML
    private void onButtonClickMyOrder() {
        SessionManager.getInstance().closeAllUserSessions();
        new MyOrdersGUI().launch();
    }






}