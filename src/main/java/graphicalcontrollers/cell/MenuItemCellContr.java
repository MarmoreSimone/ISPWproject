package graphicalcontrollers.cell;

import controller.PlaceOrderController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.beverage.Beverage;

public class MenuItemCellContr {

    private OrderBuilderGUI parent;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    @FXML
    private ImageView image;


    public void setData(Beverage bev, OrderBuilderGUI ex){

        labelName.setText(bev.getName());
        labelPrice.setText(String.valueOf(bev.getPrice())+"$");
        image.setImage(new Image(getClass().getResourceAsStream(bev.getImage())));
        parent = ex;
    }

    public void setParent(OrderBuilderGUI parent){
        this.parent = parent;
    }

    public void delete(){
        parent.elimina(labelName.getText());

    }
}
