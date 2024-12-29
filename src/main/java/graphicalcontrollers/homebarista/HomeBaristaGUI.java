package graphicalcontrollers.homebarista;

import graphicalcontrollers.GraphicalController;
import utils.SwitchPage;

public class HomeBaristaGUI extends GraphicalController {

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/homeBarista.fxml");
    }

    @Override
    public void initialize2() {
    }

}
