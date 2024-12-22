package graphicalcontrollers.Gui;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchCafeteriaGC implements Initializable{

    private PlaceOrderController controllerAppl;

    //metto string per togliere il warning
    @FXML
    private ChoiceBox<String> searchCafChoiceBox;

    @FXML
    private TextField choseSearch;

    @FXML
    private ListView<String> listCafeterias;

    private final String[] choices = {"name","city or address","get all"};

    @FXML
    private Pane paneInfoCard;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelNumber;

    @FXML
    private Label labelDescr;

    @FXML
    private Label labelName;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        paneInfoCard.setVisible(false);
        //popolo la choicebox
        searchCafChoiceBox.getItems().addAll(choices);
        //istanzio un nuovo controllore applicativo da usare durante lo svolgimento del caso d'uso
        controllerAppl = new PlaceOrderController();
    }


    public List<SearchCafeteriaBean> getCafeterias(){
        List<SearchCafeteriaBean> cafeterias = new ArrayList<>();
        SearchCafeteriaBean bean;

        switch (searchCafChoiceBox.getValue()){
            case "name":
                //nella bean metto solo il nome
                bean = new SearchCafeteriaBean(choseSearch.getText(),null);
                //fai il controllo se null
                //siccome mi ritorna sempre una lista prendo solo il primo elemento
                cafeterias.add(controllerAppl.searchCafeterias(bean).getFirst());
                break;

            /*
            case "address":
                //nella bean metto solo l'indirizzo
                SearchCafeteriaBean bean2 = new SearchCafeteriaBean(null, searchCafChoiceBox.getValue());
                controllerAppl.getCafeteriaByAddress(bean2);
                break;
            */

            case "get all":
                bean = new SearchCafeteriaBean(null,null);
                cafeterias = controllerAppl.searchCafeterias(bean);
                break;
            default:
        }

        return cafeterias;
    }

    /*
    public void searchCafeBtn(){

        List<SearchCafeteriaBean> cafeterias = getCafeterias();

        List<String> items = new ArrayList<>();


        for(SearchCafeteriaBean cafeteria : cafeterias){
            items.add(cafeteria.getName());
        }

        listCafeterias.getItems().clear();
        listCafeterias.getItems().addAll(items);

        //listener per poter cliccare direttamente sulla view
        listCafeterias.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                String selectedCafe = listCafeterias.getSelectionModel().getSelectedItem();
                System.out.println(selectedCafe);
                showSelectedCafeteria(new SearchCafeteriaBean(selectedCafe,null));
            }
        });

    }
    */


    private ChangeListener<String> listener; // Variabile membro per tracciare il listener

    public void searchCafeBtn() {

        List<SearchCafeteriaBean> cafeterias = getCafeterias();

        List<String> items = new ArrayList<>();

        for (SearchCafeteriaBean cafeteria : cafeterias) {
            items.add(cafeteria.getName() + "  |  " + cafeteria.getAddress());
        }

        // Aggiorno la vista con i nuovi elementi
        listCafeterias.getItems().clear();
        listCafeterias.getItems().addAll(items);

        //Controllo se il listener è già stato creato
        if (listener != null) {
            //Rimuovo il listener precedente
            listCafeterias.getSelectionModel().selectedItemProperty().removeListener(listener);
        }

        //Crea il nuovo listener
        listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                int selectedCafe = listCafeterias.getSelectionModel().getSelectedIndex();
                if(selectedCafe != -1)
                    showSelectedCafeteria(cafeterias.get(selectedCafe));
            }
        };

        // Aggiungi il nuovo listener
        listCafeterias.getSelectionModel().selectedItemProperty().addListener(listener);
    }


    public void showSelectedCafeteria(SearchCafeteriaBean cafe){
        CafeteriaBean bean = controllerAppl.loadSelectedCafeteria(cafe);
        labelName.setText(bean.getName());
        labelAddress.setText(bean.getAddress());
        labelCity.setText(bean.getCity());
        labelNumber.setText(bean.getNumber());
        labelDescr.setText(bean.getDescription());
        paneInfoCard.setVisible(true);
    }

}
