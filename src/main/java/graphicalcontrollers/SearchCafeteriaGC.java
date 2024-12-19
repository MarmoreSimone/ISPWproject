package graphicalcontrollers;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.PlaceOrderController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchCafeteriaGC implements Initializable {
    //implemento initializable per usare la choice box

    //metto string per togliere il warning
    @FXML
    private ChoiceBox<String> searchCafChoiceBox;

    @FXML
    private TextField choseSearch;

    private final String[] choices = {"name","address","get all"};

    //per popolare la choicebox
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        searchCafChoiceBox.getItems().addAll(choices);
    }

    public List<CafeteriaBean> getCafeterias(){
        PlaceOrderController controllerAppl = new PlaceOrderController();
        List<CafeteriaBean> cafeterias = new ArrayList<>();

        switch (searchCafChoiceBox.getValue()){
            case "name":
                //nella bean metto solo il nome
                SearchCafeteriaBean bean = new SearchCafeteriaBean(choseSearch.getText(),null);
                //fai il controllo se null
                cafeterias.add(controllerAppl.getCafeteriaByName(bean));
                break;
            /*
            case "address":
                //nella bean metto solo l'indirizzo
                SearchCafeteriaBean bean2 = new SearchCafeteriaBean(null, searchCafChoiceBox.getValue());
                controllerAppl.getCafeteriaByAddress(bean2);
                break;

            */

            case "get all":
                cafeterias = controllerAppl.getAllCafeterias();
                break;
            default:
        }

        return cafeterias;
    }

    @FXML
    private ListView<String> listCafeterias;

    @FXML
    private Button searchBtn;


    public void clickBtnSearch(){

        List<CafeteriaBean> cafeterias = getCafeterias();

        List<String> items = new ArrayList<>();

        for(CafeteriaBean cafeteria : cafeterias){
            items.add(cafeteria.getName() + "---" + cafeteria.getAddress());
        }

        listCafeterias.getItems().clear();
        listCafeterias.getItems().addAll(items);

    }
}
