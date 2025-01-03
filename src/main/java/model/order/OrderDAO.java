package model.order;

import java.util.List;

public abstract class OrderDAO {

    public Order createNewOrder(){
        return new Order();
    }

    public abstract void saveOrder(Order order, String orderReq, String cafeteria);

    public abstract List<Order> getAllOrders();

    public abstract Order getOrderByOrderReq(String orderReq, String cafeteria);

}
