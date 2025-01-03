package model.order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOdemo extends OrderDAO {

    private static OrderDAOdemo instance = null;
    private static List<Order> orders = new ArrayList<Order>();

    public static synchronized OrderDAOdemo getInstance() {
        if(OrderDAOdemo.instance == null)
            OrderDAOdemo.instance = new OrderDAOdemo();
        return OrderDAOdemo.instance;
    }


    protected OrderDAOdemo() {

    }

    public void saveOrder(Order order, String orderReq, String cafeteria) {
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderByOrderReq(String orderReq, String cafeteria){
        return null;
    }



}
