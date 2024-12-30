package starter;

import graphicalcontrollers.DummyGC;
import graphicalcontrollers.login.LoginGUI;

import graphicalcontrollers.processorders.ProcessOrderGUI;

import javafx.fxml.FXML;


public class MenuControllerBarista {


    @FXML
    private void process() {
        new ProcessOrderGUI().launch();
    }


    @FXML
    private void logout() {
            new LoginGUI().launch();
    }


    @FXML
    private void dummy() {
            new DummyGC().launch();
    }




}