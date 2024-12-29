package utils;
import bean.BeverageBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.cell.AddedBevCellContr;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import starter.Main;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

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
        GraphicalController contr = loader.getController();
        contr.initialize2();

        this.stage.setScene(scene);
        this.stage.show();
    }

    public void changePage(String sourcePath, PlaceOrderController controller){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sourcePath));
        Scene scene;


        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        GraphicalController contr = loader.getController();
        contr.setControllerApplPlaceOrder(controller);

        contr.initialize2();
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void changePage(String sourcePath, OrderBuilderGUI controller, BeverageBean beverage){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sourcePath));
        Scene scene;


        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        GraphicalController contr = loader.getController();
        contr.setContrOrderBuilder(controller);
        contr.setBeverage(beverage);

        contr.initialize2();
        this.stage.setScene(scene);
        this.stage.show();
    }



    public void changeMiniPage(String sourcePathCell, VBox vbox, GraphicalController controllerParent, List<Object> objects){
        vbox.getChildren().clear();

        for(int i=0 ; i<objects.size(); i++){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(sourcePathCell));

            try{
                Pane pane = loader.load();
                GraphicalController contr = loader.getController();
                contr.setData(objects.get(i),controllerParent);
                vbox.getChildren().add(pane);
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }


    //usato all'inizio
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
