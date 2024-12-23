package graphicalcontrollers.orderbuilder;

import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import utils.SwitchPage;

public class OrderBuilderGUI extends GraphicalController {

    private PlaceOrderController controllerAppl;

    //overload dell'operazione launch()
    public void launch(PlaceOrderController controller) {
        SwitchPage.getSwitchPageInstance().changePage("/view/orderBuilderGUI.fxml", controller);

    }

    @Override
    public void setControllerApplPlaceOrder(PlaceOrderController controllerAppl) {
        this.controllerAppl = controllerAppl;
    }

    public void initialize2() {
        System.out.println(controllerAppl.getCafeteriaName());
        }



    }


