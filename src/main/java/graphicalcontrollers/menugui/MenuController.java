package graphicalcontrollers.menugui;

import engineering.SessionManager;
import graphicalcontrollers.login.LoginGUI;
import graphicalcontrollers.myorders.MyOrdersGUI;
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
    private void onButtonClickMyOrder() {
        SessionManager.getInstance().closeAllUserSessions();
        new MyOrdersGUI().launch();
    }






}