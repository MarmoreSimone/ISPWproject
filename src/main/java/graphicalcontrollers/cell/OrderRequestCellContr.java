package graphicalcontrollers.cell;

import bean.BeverageBean;
import bean.OrderBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.orderbuilder.OrderBuilderGUI;
import graphicalcontrollers.processOrders.ProcessOrderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class OrderRequestCellContr extends GraphicalController {


    @FXML
    private Text date;

    @FXML
    private Label labelName;

    @FXML
    private Text note;

    @FXML
    private TextArea rejReson;

    @FXML
    private Text time;

    @FXML
    private ListView<String> itemList;

    private ProcessOrderGUI parent;

    private OrderBean order;

    @Override
    public void setData(Object ord, GraphicalController contr){
        order = (OrderBean) ord;
        parent = (ProcessOrderGUI) contr;

        time.setText(order.getTime());
        date.setText(order.getDate());
        note.setText(order.getNote());


        for(int i=0;i<order.getBevs().size();i++){
            itemList.getItems().add(order.getBevs().get(i).getName() + "  " + order.getBevs().get(i).getPrice() + "$");
        }


    }

    public void acceptClick(){
        parent.accept(order);
    }

    public void rejectClick(){
        parent.reject(order);
    }

}
