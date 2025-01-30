package graphicalcontrollers.searchcafeteria;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import controller.SearchCafeteriaController;
import engineering.SessionManager;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.SwitchPage;

import java.util.ArrayList;
import java.util.List;

public class SearchCafeteriaGUI extends GraphicalController {

    private SearchCafeteriaController controllerAppl;

    private final String[] choices = {"get all","name","city or address"};

    //metto string per togliere il warning
    @FXML
    private ChoiceBox<String> searchCafChoiceBox;

    @FXML
    private TextField choseSearch;

    @FXML
    private ListView<String> listCafeterias;

    @FXML
    private ComboBox<String> openHour;

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
    private ImageView imageCafe;

    @Override
    public void initialize2() {

        paneInfoCard.setVisible(false);
        orderBtn.setVisible(false);
        //popolo la choicebox
        searchCafChoiceBox.getItems().addAll(choices);
        searchCafChoiceBox.setValue(choices[0]);

        controllerAppl = new SearchCafeteriaController();

        searchCafe();
    }

    @Override
    public void launch(){
        //si potrebbe anche fare passando allo switch page questa istanza di controller grafico cosi da non crearne un'altra
        SwitchPage.getSwitchPageInstance().changePage("/view/searchCafeteriaGUI.fxml");
    }

    //devo fare attenzione a non sovrapporre i listener a ogni click del bottone
    private ChangeListener<String> listener;
    public void searchCafe() {

        List<CafeteriaBean> cafeterias = getCafeterias();

        //caso in cui ho un solo elemento, faccio comparire la infocard appena si preme search
        if(cafeterias.size() == 1){
            orderBtn.setVisible(true);
            showSelectedCafeteria(cafeterias.getFirst());
        }

        //usata per popolare la listview
        List<String> items = new ArrayList<>();

        for (CafeteriaBean cafeteria : cafeterias) {
            items.add(cafeteria.getName() + cafeteria.getDistance());
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

    public List<CafeteriaBean> getCafeterias(){
        List<CafeteriaBean> cafeterias = new ArrayList<>();
        SearchCafeteriaBean bean = new SearchCafeteriaBean();

        try {

            switch (searchCafChoiceBox.getValue()) {
                case "name":
                    bean.setName(choseSearch.getText());
                    cafeterias.add(controllerAppl.getCafeteriaByName(bean));
                    break;

                case "city or address":
                    bean.setAddress(choseSearch.getText());
                    cafeterias = controllerAppl.getCafeByAddress(bean);
                    break;

                case "get all":
                    cafeterias = controllerAppl.getAllCafeterias();
                    break;

                default:
            }

        } catch (NoCafeteriasFoundException | SystemErrorException e){
            e.showException();
        }

        return cafeterias;
    }


    public void showSelectedCafeteria(CafeteriaBean cafe){

        labelName.setText(cafe.getName());
        labelAddress.setText(cafe.getAddress());
        labelCity.setText(cafe.getCity());
        labelNumber.setText(cafe.getNumber());
        labelDescr.setText(cafe.getDescription());
        imageCafe.setImage(new Image(getClass().getResourceAsStream(cafe.getPhoto())));
        openHour.getItems().clear();
        paneInfoCard.setVisible(true);

    }

    public void continueOrder(){

        //creo la sessione del controll appl.
        String session = SessionManager.getInstance().newPlaceOrderSession();
        //imposto la caffetteria selezionata
        PlaceOrderController contr = new PlaceOrderController(session);
        try {
            contr.setCafeteria(labelName.getText());
        } catch (SystemErrorException e) {
            e.showException();
        }

        new OrderBuilderGUI().launch(session);

    }


}