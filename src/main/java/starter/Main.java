package starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DAOfactory;
import utils.SwitchPage;
import viewcli.MainMenuCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 720);

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Hello!");
        SwitchPage curr = SwitchPage.getSwitchPageInstance();
        curr.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        String input2 = null;

        try {

            System.out.println("persistenza:\n1) demo\n2) db\n3) file system");
            input = reader.readLine();

            System.out.println("scegli l'interfaccia:\n1) gui\n2) cli\n");
            input2 = reader.readLine();

        } catch (IOException e) {
            System.out.println("Errore durante la lettura dell'input.");
        }

        //imposto la DAOfactory
        DAOfactory.setDAOfactory(Integer.parseInt(input));

        if(input2.equals("1")) {
            launch();
        }
        else{
            new MainMenuCLI().start();
        }


    }
}

