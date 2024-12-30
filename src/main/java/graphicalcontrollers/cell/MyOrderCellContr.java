package graphicalcontrollers.cell;

import bean.OrderRequestBean;
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

    private OrderRequestBean orderReq;

    public void initialize2() {

    }

    @Override
    public void setData(Object ord, GraphicalController contr){
        orderReq = (OrderRequestBean) ord;

        cafeName.setText(orderReq.getCafe());
        code.setText(orderReq.getCode());
        date.setText(orderReq.getOrder().getDate());
        time.setText(orderReq.getOrder().getTime());
        tot.setText(String.valueOf(orderReq.getOrder().getTotPrice()) + "$");
        status.setText(orderReq.getState());

        for(int i = 0; i< orderReq.getOrder().getBevs().size(); i++){
            itemList.getItems().add(orderReq.getOrder().getBevs().get(i).getName() + "  " + orderReq.getOrder().getBevs().get(i).getPrice() + "$");
        }
    }


}
