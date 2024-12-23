package utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import starter.Main;

import java.io.IOException;

public class SwitchPage {
//lo faccio singleton così da mantenere il riferimento allo stage

    private static SwitchPage instance = null;

    private Stage stage;

    protected SwitchPage() {}

    public static SwitchPage getSwitchPageInstance() {
        if(SwitchPage.instance == null)
            SwitchPage.instance = new SwitchPage();
        return SwitchPage.instance;
    }

    public void changePage(String sourcePath){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sourcePath));
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.stage.setScene(scene);
        this.stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
