package graphicalcontrollers.home;

import graphicalcontrollers.GraphicalController;
import utils.SwitchPage;

public class HomeGUI extends GraphicalController {

    @Override
    public void launch() {
        SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml");
    }


}
