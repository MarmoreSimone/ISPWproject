package starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DAOfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 720);

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("SCEGLI LA PERSISTENZA:");
        System.out.println("1) DEMO\n2) DB\n3) FILE SYSTEM");

        String input = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Errore durante la lettura dell'input.");
        }

        //imposto la DAOfactory
        DAOfactory.setDAOfactory(Integer.parseInt(input));

        //test per vedere se ha preso quella giusta
        DAOfactory fabrica = DAOfactory.getDAOfactory();
        fabrica.createCafeteriaDAO();

        launch();
    }
}

