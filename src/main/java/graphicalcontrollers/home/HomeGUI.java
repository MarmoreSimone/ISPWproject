package graphicalcontrollers.home;

import graphicalcontrollers.GraphicalController;
import utils.SwitchPage;

public class HomeGUI extends GraphicalController {

    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml");
    }

    @Override
    public void initialize2() {
    }

}
