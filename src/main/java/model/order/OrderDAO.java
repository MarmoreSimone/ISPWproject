package model.order;

import exception.SystemErrorException;

public abstract class OrderDAO {

    public Order createNewOrder(){
        return new Order();
    }

    public abstract void saveOrder(Order order, String orderReq, String cafeteria) throws SystemErrorException;

    public abstract Order getOrderByOrderReq(String orderReq, String cafeteria) throws SystemErrorException;

}
