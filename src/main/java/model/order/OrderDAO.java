package model.order;

public abstract class OrderDAO {

    public Order createNewOrder(){
        return new Order();
    }

    public abstract void saveOrder(Order order);

}
