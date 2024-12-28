package graphicalcontrollers.home;

import bean.OrderBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import utils.SwitchPage;

public class HomeGUI extends GraphicalController {

    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/home.fxml", controller);
    }

    @Override
    public void initialize2() {
    }

}
