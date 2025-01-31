package graphicalcontrollers.cell;

import bean.OrderRequestBean;
import graphicalcontrollers.GraphicalController;
import graphicalcontrollers.GraphicalControllerCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class MyOrderCellContr extends GraphicalControllerCell {


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

            String[] parts = orderReq.getOrder().getBevs().get(i).getName().split("\n", 2);
            String firstWord = parts[0];
            String rest = parts.length > 1 ? parts[1] : "";

            itemList.getItems().add(firstWord + " " + orderReq.getOrder().getBevs().get(i).getPrice() + "$\n" + rest);

        }
    }


}
