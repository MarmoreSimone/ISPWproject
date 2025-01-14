package graphicalcontrollers.cell;

import bean.BeverageBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddedBevCellContr extends GraphicalController {

    private OrderBuilderGUI parent;

    private BeverageBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;


    @Override
    public void setData(Object bev, GraphicalController contr){
        beverage = (BeverageBean) bev;
        labelName.setText(beverage.getName());
        labelPrice.setText(String.valueOf(beverage.getPrice())+"$");
        parent = (OrderBuilderGUI) contr;
    }

    public void remove(){
        parent.removeFromOrder(beverage);
    }

}
