package graphicalcontrollers.cell;

import bean.MenuItemBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.GraphicalControllerCell;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MenuItemCellContr extends GraphicalControllerCell {

    private OrderBuilderGUI parent;

    private MenuItemBean beverage;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    @FXML
    private ImageView image;


    @Override
    public void setData(Object bev, GraphicalController contr){
        beverage = (MenuItemBean) bev;
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
