package starter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private AnchorPane ancora;

    @FXML
    private void onButtonClickPlaceOrder() {
        try {
            changeScene("/view/placeOrderGUI.fxml");  // Cambia scena a menu.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonClickDummy() {
        try {
            changeScene("/view/dummy.fxml");  // Cambia scena a order.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonClickGift() {
        try {
            changeScene("/view/giftCouponGUI.fxml");  // Cambia scena a order.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonClickProfile() {
        try {
            changeScene("/view/order.fxml");  // Cambia scena a order.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonClickMyOrder() {
        try {
            changeScene("/view/myOrdersGUI.fxml");  // Cambia scena a order.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeScene(String filepath) throws IOException {
        // Carica il nuovo file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filepath));
        Parent root = loader.load();  // Carica il contenuto del nuovo FXML

        // Ottieni la finestra corrente (Stage)
        Stage currentStage = (Stage) ancora.getScene().getWindow();

        // Crea una nuova scena
        Scene newScene = new Scene(root);

        // Imposta la nuova scena sulla finestra corrente
        currentStage.setScene(newScene);

        // Mostra la finestra con la nuova scena
        currentStage.show();
    }




}