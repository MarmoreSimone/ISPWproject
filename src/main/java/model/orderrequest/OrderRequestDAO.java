package model.orderrequest;

import java.util.List;

public abstract class OrderRequestDAO {

    public OrderRequest createNewOrder(){
        return new OrderRequest();
    }

    public abstract void saveOrderRequest(OrderRequest order);

    public abstract List<OrderRequest> getAllOrderRequestsByCafeName(String cafeteria);

    public abstract List<OrderRequest> getAllOrderRequestsByUsername(String user);

    public abstract void changeStatus(OrderRequest order, String status);
}
