package graphicalcontrollers.cell;

import bean.BeverageBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddedBevCellContr extends GraphicalController {

    private OrderBuilderGUI parent;

    private BeverageBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    /*
    public void setData(BeverageBean bev, OrderBuilderGUI contr){
        beverage = bev;
        labelName.setText(bev.getName());
        labelPrice.setText(String.valueOf(bev.getPrice())+"$");
        parent = contr;
    }
    */

    //TODO vedi se può andare bene
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
