package model.order;

import java.util.List;

public abstract class OrderDAO {

    public Order createNewOrder(){
        return new Order();
    }

    public abstract void saveOrder(Order order);

    public abstract List<Order> getAllOrders();


}
