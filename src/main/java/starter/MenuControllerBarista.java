package starter;

import graphicalcontrollers.DummyGC;
import graphicalcontrollers.login.LoginGUI;
import graphicalcontrollers.myorders.MyOrdersGUI;
import graphicalcontrollers.processorders.ProcessOrderGUI;
import graphicalcontrollers.searchcafeteria.SearchCafeteriaGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.SwitchPage;

import java.io.IOException;

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