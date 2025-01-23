package starter;

import engineering.SessionManager;
import graphicalcontrollers.login.LoginGUI;

import graphicalcontrollers.processorders.ProcessOrderGUI;

import javafx.fxml.FXML;


public class MenuControllerBarista {


    @FXML
    private void process() {
        SessionManager.getInstance().closeAllUserSessions();
        new ProcessOrderGUI().launch();
    }


    @FXML
    private void logout() {
        SessionManager.getInstance().closeAllUserSessions();
        new LoginGUI().launch();
    }


    @FXML
    private void dummy() {

    }




}