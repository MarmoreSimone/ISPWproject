package controller;

import bean.BeverageBean;
import bean.OrderBean;
import bean.SearchCafeteriaBean;
import model.DAOfactory;
import model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersRequestController {

    PlaceOrderController placeOrderController = new PlaceOrderController();

    public OrdersRequestController() {

    }

    public List<OrderBean> getAllRequest(){
        List<Order> orders;
        List<OrderBean> ordersBean = new ArrayList<>();

        orders = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();

        for (Order order : orders) {
            if(order.getStatus().equals("PENDING"))
            ordersBean.add(new OrderBean(placeOrderController.getBeveragesBeanList(order.getBevs()),placeOrderController.loadSelectedCafeteria(new SearchCafeteriaBean(order.getCafeteria().getName(), order.getCafeteria().getAddress())), order.getTotPrice(), order.getPickUpCode(), order.getPayMethod(), order.getNote(), order.getDate(),order.getTime()));

        }

        return ordersBean;

    }

    public Order getOrderFromBean(OrderBean bean) {

        List<Order> orders = DAOfactory.getDAOfactory().createOrderDAO().getAllOrders();

        for (Order order : orders) {
            //il pickUpCode è generato randomicamente la prob che ci siano più ordini con lo stesso pickUpCode più stesso orario e data è molto bassa TODO metti pure nome caff e vai sul sicuro
            if(bean.getPickUpCode().equals(order.getPickUpCode()) && bean.getDate().equals(order.getDate()) && order.getTime().equals(order.getTime())) {
                return order;
            }
        }
        return null;
    }

    public void acceptRequest(OrderBean orderBean) {
        Order order = getOrderFromBean(orderBean);
        order.setStatus("ACCEPTED");
    }

    public void rejectRequest(OrderBean orderBean) {
        Order order = getOrderFromBean(orderBean);
        order.setStatus("REJECTED");
    }
}