package graphicalcontrollers.cell;

import bean.MenuItemBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddedBevCellContr extends GraphicalController {

    private OrderBuilderGUI parent;

    private MenuItemBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;


    @Override
    public void setData(Object bev, GraphicalController contr){
        beverage = (MenuItemBean) bev;
        labelName.setText(beverage.getName());
        labelPrice.setText(String.valueOf(beverage.getPrice())+"$");
        parent = (OrderBuilderGUI) contr;
    }

    public void remove(){
        System.out.println("hai cliccato su: " + beverage.getName());
        parent.removeFromOrder(beverage);
    }

}
