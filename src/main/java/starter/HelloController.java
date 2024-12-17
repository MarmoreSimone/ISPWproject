package starter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button placeOrderButton;

    @FXML
    private void goToPlaceOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/placeOrder.fxml"));
            Parent root = loader.load();

            // Imposta la nuova scena sulla finestra
            Stage stage = (Stage) placeOrderButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}