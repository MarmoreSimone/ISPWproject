package graphicalcontrollers.homebarista;

import engineering.SessionManager;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.user.Barista;
import utils.SwitchPage;

public class HomeBaristaGUI extends GraphicalController {

    @FXML
    private Text name;

    @Override
    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/homeBarista.fxml");
    }

    @Override
    public void initialize2() {

        Barista user = SessionManager.getInstance().getUserBaristaLogged();
        name.setText(user.getCafeteria().getName());

    }

}
