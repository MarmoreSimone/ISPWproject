package graphicalcontrollers.cell;

import bean.BeverageBean;
import controller.PlaceOrderController;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.beverage.Beverage;

public class MenuItemCellContr extends GraphicalController {

    private OrderBuilderGUI parent;

    private BeverageBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    @FXML
    private ImageView image;

    /*
    public void setData(BeverageBean bev, OrderBuilderGUI contr){
        beverage = bev;
        labelName.setText(bev.getName());
        labelPrice.setText(String.valueOf(bev.getPrice())+"$");
        image.setImage(new Image(getClass().getResourceAsStream(bev.getImage())));
        parent = contr;
    }
     */

    public void initialize2() {

    }


    //TODO vedi se può andare bene
    @Override
    public void setData(Object bev, GraphicalController contr){
        beverage = (BeverageBean) bev;
        labelName.setText(beverage.getName());
        labelPrice.setText(String.valueOf(beverage.getPrice())+"$");
        image.setImage(new Image(getClass().getResourceAsStream(beverage.getImage())));
        parent = (OrderBuilderGUI) contr;
    }


    public void addOrder(){
        parent.addToOrder(beverage);
    }

    public void custom(){
        parent.customizeBev(beverage);
    }


}
