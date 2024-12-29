package graphicalcontrollers.cell;

import bean.OrderBean;
import graphicalcontrollers.GraphicalController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class MyOrderCellContr extends GraphicalController {


    @FXML
    private Text cafeName;

    @FXML
    private Text code;

    @FXML
    private Text date;

    @FXML
    private ListView<String> itemList;

    @FXML
    private Text status;

    @FXML
    private Text time;

    @FXML
    private Text tot;

    private OrderBean order;

    @Override
    public void setData(Object ord, GraphicalController contr){
        order = (OrderBean) ord;

        cafeName.setText(order.getCafeName());
        code.setText(order.getPickUpCode());
        date.setText(order.getDate());
        time.setText(order.getTime());
        tot.setText(String.valueOf(order.getTotPrice()) + "$");
        status.setText(order.getStatus());

        for(int i=0;i<order.getBevs().size();i++){
            itemList.getItems().add(order.getBevs().get(i).getName() + "  " + order.getBevs().get(i).getPrice() + "$");
        }
    }


}
