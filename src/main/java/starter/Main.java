package starter;

import graphicalcontrollers.login.LoginCLI;
import graphicalcontrollers.popup.PopupFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DAOfactory;
import utils.Dummy;
import utils.SwitchPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 720);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Hello!");
        //salvo stage in switchPage
        SwitchPage curr = SwitchPage.getSwitchPageInstance();
        curr.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {

            System.out.println("persistance:\n1) demo\n2) db\n3) file system");
            input = reader.readLine();

            //imposto la DAOfactory
            DAOfactory.setDAOfactory(Integer.parseInt(input));

            System.out.println("interface:\n1) gui\n2) cli\n");
            input = reader.readLine();

            Dummy.dummyRegistration();

            if(input.equals("1")) {
                PopupFactory.setfactory(1);
                launch();
            }
            else{
                PopupFactory.setfactory(2);
                new LoginCLI().launch();
            }



        } catch (IOException e) {
            System.out.println("error");
        }


    }
}

