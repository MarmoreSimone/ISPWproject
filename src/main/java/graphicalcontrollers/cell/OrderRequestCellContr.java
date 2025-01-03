package graphicalcontrollers.cell;

import bean.OrderRequestBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.processorders.ProcessOrderGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class OrderRequestCellContr extends GraphicalController {


    @FXML
    private Text date;

    @FXML
    private Text note;

    @FXML
    private TextArea rejReson;

    @FXML
    private Text time;

    @FXML
    private Label labelName;

    @FXML
    private ListView<String> itemList;

    private ProcessOrderGUI parent;

    private OrderRequestBean order;



    @Override
    public void setData(Object ord, GraphicalController contr){
        order = (OrderRequestBean) ord;
        parent = (ProcessOrderGUI) contr;

        time.setText(order.getOrder().getTime());
        date.setText(order.getOrder().getDate());
        note.setText(order.getOrder().getNote());
        System.out.println(order.getUsername());
        labelName.setText(order.getUsername());


        for(int i=0;i<order.getOrder().getBevs().size();i++){
            itemList.getItems().add(order.getOrder().getBevs().get(i).getName() + "  " + order.getOrder().getBevs().get(i).getPrice() + "$");
        }


    }

    public void acceptClick(){
        parent.accept(order);
    }

    public void rejectClick(){
        parent.reject(order,rejReson.getText());
    }

}
