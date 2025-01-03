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

    public List<OrderRequest> getAllOrderRequestsByCafeName(String cafeteria) {
        //evito di modificare gli elementi in orders request
        List<OrderRequest> list = new ArrayList<>(ordersRequest);

        for(int i=0;i<list.size();i++){
            if(!list.get(i).getCafeteria().getName().equals(cafeteria)){
                list.remove(i);
            }
        }
        return list;
    }

    public  List<OrderRequest> getAllOrderRequestsByUsername(String user){
        List<OrderRequest> list = new ArrayList<>(ordersRequest);

        for(int i=0;i<list.size();i++){
            if(!list.get(i).getUser().equals(user)){
                list.remove(i);
            }
        }

        return list;

    }

    public void changeStatus(OrderRequest order, String status){
        order.setStatus(status);
    }


}
