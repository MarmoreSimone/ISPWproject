package graphicalcontrollers.searchcafeteria;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchCafeteriaGUI implements GraphicalController, Initializable, SearchCafeteriaInterface {

    private PlaceOrderController controllerAppl;

    private final String[] choices = {"name","city or address","get all"};

    //metto string per togliere il warning
    @FXML
    private ChoiceBox<String> searchCafChoiceBox;

    @FXML
    private TextField choseSearch;

    @FXML
    private ListView<String> listCafeterias;

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
    private Button orderBtn;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {

        paneInfoCard.setVisible(false);
        orderBtn.setVisible(false);
        //popolo la choicebox
        searchCafChoiceBox.getItems().addAll(choices);
        //istanzio un nuovo controllore applicativo da usare durante lo svolgimento del caso d'uso
        controllerAppl = new PlaceOrderController();
    }

    public void launch(){
        //si potrebbe anche fare passando allo switch page questa istanza di controller grafico cosi da non crearne un'altra
        SwitchPage.getSwitchPageInstance().changePage("/view/searchCafeteriaGUI.fxml");
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

            //TODO caso in cui si vuole cercare per indirizzo/città

            case "get all":
                bean = new SearchCafeteriaBean(null,null);
                cafeterias = controllerAppl.searchCafeterias(bean);
                break;
            default:
        }

        return cafeterias;
    }

    //devo fare attenzione a non sovrapporre i listener a ogni click del bottone
    private ChangeListener<String> listener;
    public void searchCafe() {

        List<SearchCafeteriaBean> cafeterias = getCafeterias();

        //caso in cui ho un solo elemento, faccio comparire la infocard appena si preme search
        if(cafeterias.size() == 1){
            showSelectedCafeteria(cafeterias.getFirst());
            }

        //usata per popolare la listview
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
                orderBtn.setVisible(true);
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
