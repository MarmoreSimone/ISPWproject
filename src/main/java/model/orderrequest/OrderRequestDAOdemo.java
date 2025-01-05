package model.orderrequest;


import java.util.ArrayList;
import java.util.Comparator;
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

    public List<OrderRequest> getAllOrderRequestsByCafeName(String cafeteria) {
        //evito di modificare gli elementi in orders request
        List<OrderRequest> list = new ArrayList<>(ordersRequest);

        //for(int i=0;i<list.size();i++){
        for (int i = list.size() - 1; i >= 0; i--) {
            if(!list.get(i).getCafeteria().getName().equals(cafeteria)){
                list.remove(i);
            }
        }

        //ordino lista in base alla data
        list.sort((o1, o2) -> o2.getOrder().getDate().compareTo(o1.getOrder().getDate()));
        return list;
    }

    public  List<OrderRequest> getAllOrderRequestsByUsername(String user){
        List<OrderRequest> list = new ArrayList<>(ordersRequest);

        for (int i = list.size() - 1; i >= 0; i--) {
            if(!list.get(i).getUser().equals(user)){
                list.remove(i);
            }
        }

        list.sort((o1, o2) -> o2.getOrder().getDate().compareTo(o1.getOrder().getDate()));
        return list;

    }

    public void changeStatus(OrderRequest order, String status){
        order.setStatus(status);
    }


}
