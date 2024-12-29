package controller;


import bean.OrderBean;
import bean.SearchCafeteriaBean;
import model.DAOfactory;
import model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersRequestController {

    PlaceOrderController placeOrderController;
    SearchCafeteria searchCafeteriaController;

    public OrdersRequestController() {
        placeOrderController = new PlaceOrderController();
        searchCafeteriaController = new SearchCafeteria();
    }

    public List<OrderBean> getAllRequest(){
        List<Order> orders;
        List<OrderBean> ordersBean = new ArrayList<>();

        orders = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();

        for (Order order : orders) {
            if(order.getStatus().equals("PENDING")) {
                ordersBean.add(placeOrderController.getOrder(order));
            }
        }

        return ordersBean;

    }

    public Order getOrderFromBean(OrderBean bean) {

        List<Order> orders = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();

        for (Order order : orders) {
            //il pickUpCode è generato randomicamente la prob che ci siano più ordini con lo stesso pickUpCode più stesso orario e data è molto bassa TODO metti pure nome caff e vai sul sicuro
            if(bean.getPickUpCode().equals(order.getPickUpCode()) && bean.getDate().equals(order.getDate()) && bean.getTime().equals(order.getTime())) {
                return order;
            }
        }
        return null;
    }

    public void acceptRequest(OrderBean orderBean) {
        Order order = getOrderFromBean(orderBean);
        order.setStatus("ACCEPTED");
    }

    public void rejectRequest(OrderBean orderBean,String reason) {
        Order order = getOrderFromBean(orderBean);
        if(reason == null){
            order.setStatus("REJECTED");
        }
        else {
            order.setStatus("REJECTED: " + reason);
        }
    }

}
