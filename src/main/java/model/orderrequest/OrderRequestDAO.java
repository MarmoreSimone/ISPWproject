package model.orderrequest;

import exception.SystemErrorException;

import java.util.List;

public abstract class OrderRequestDAO {

    public OrderRequest createNewOrder(){
        return new OrderRequest();
    }

    public abstract void saveOrderRequest(OrderRequest order);

    public abstract List<OrderRequest> getAllOrderRequestsByCafeName(String cafeteria) throws SystemErrorException;

    public abstract List<OrderRequest> getAllOrderRequestsByUsername(String user) throws SystemErrorException;

    public abstract void changeStatus(OrderRequest order, String status);
}
