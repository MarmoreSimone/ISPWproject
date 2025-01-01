package graphicalcontrollers.homebarista;

import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import utils.SwitchPage;
import utils.UserLogged;

public class HomeBaristaGUI extends GraphicalController {

    @FXML
    private Text name;

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/homeBarista.fxml");
    }

    public void initialize2() {

        name.setText(UserLogged.getInstance().getUser().getCafeteria());

    }

}
