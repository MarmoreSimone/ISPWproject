package model.order;

public abstract class OrderDAO {

    public Order createNewOrder(){
        return new Order();
    }

    public abstract void saveOrder(Order order, String orderReq, String cafeteria);

    public abstract Order getOrderByOrderReq(String orderReq, String cafeteria);

}
