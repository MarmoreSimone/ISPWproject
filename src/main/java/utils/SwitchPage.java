package utils;

import graphicalcontrollers.GraphicalController;

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



    //cambia semplicemente la scena
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


    public void changePage(String sourcePath, String session){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sourcePath));
        Scene scene;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //qui uso il generico GraphicalController dato che non posso sapere a priori il tipo di controller associato all'fxml, in questo modo basta che il controller associato definisca l'operazione
        //setControllerApplPlaceOrder() e si può impostare il controllore applicativo
        GraphicalController contr = loader.getController();
        contr.setSession(session);

        contr.initialize2();
        this.stage.setScene(scene);
        this.stage.show();
    }




    //usato per quando devo usare le Cell, il primo parametro è la stringa che contiene l'indirizzo dell'fxml della cella
    //il secondo è la Vbox a cui aggiungere le singole Cell
    //il terzo è l'istanza corrente del controllore da cui faccio "partire" le Cell e che contiene le operazioni principali richiamate dal mini controllore della Cell
    //il quarto è una lista generica di oggetti dove ogni oggetto contiente le informazioni per popolare la rispettiva Cell
    //prima di chiamare ChangeMiniPage() la lista di oggetti va castata a lista generica in questo modo non c'è bisogno di definire
    //una operazione specifica per ogni tipo di oggetto.
    //la prima cosa che farà setData() sarà quella di ricastare l'oggetto generico ad un tipo specifico e questo lo può fare
    //in quanto è a conoscenza del tipo di oggetto che si aspetta
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
