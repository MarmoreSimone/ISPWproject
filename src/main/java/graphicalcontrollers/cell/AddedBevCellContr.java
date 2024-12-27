package graphicalcontrollers.cell;

import bean.BeverageBean;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddedBevCellContr {

    private OrderBuilderGUI parent;

    private BeverageBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;


    public void setData(BeverageBean bev, OrderBuilderGUI contr){
        beverage = bev;
        labelName.setText(bev.getName());
        labelPrice.setText(String.valueOf(bev.getPrice())+"$");
        parent = contr;
    }

    public void remove(){
        parent.removeFromOrder(beverage);
    }

}
