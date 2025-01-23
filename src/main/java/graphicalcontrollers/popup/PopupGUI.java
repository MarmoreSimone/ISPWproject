package graphicalcontrollers.popup;

import exception.SystemErrorException;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import starter.Main;
import utils.SwitchPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupGUI implements Initializable, Popup {

    @FXML
    private Text text;

    String message;
    Stage stage;

    public void show(String msg){

        this.message = msg;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/popup.fxml"));
        fxmlLoader.setController(this);

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("system error");
        }


        stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("error message");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        text.setText(message);
    }


    public void close(){
        stage.close();
    }


}
