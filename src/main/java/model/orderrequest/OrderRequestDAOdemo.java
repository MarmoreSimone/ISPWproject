package model.orderrequest;


import java.util.ArrayList;
import java.util.List;

public class OrderRequestDAOdemo extends OrderRequestDAO {

    private static OrderRequestDAOdemo instance = null;
    private static List<OrderRequest> ordersRequest = new ArrayList<OrderRequest>();

    public static synchronized OrderRequestDAOdemo getInstance() {
        if(OrderRequestDAOdemo.instance == null)
            OrderRequestDAOdemo.instance = new OrderRequestDAOdemo();
        return OrderRequestDAOdemo.instance;
    }


    protected OrderRequestDAOdemo() {

    }

    public void saveOrderRequest(OrderRequest order) {
        ordersRequest.add(order);
    }

    public List<OrderRequest> getAllOrderRequests() {
        return ordersRequest;
    }

}
