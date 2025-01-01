package controller;

import bean.OrderRequestBean;
import model.DAOfactory;
import model.orderrequest.OrderRequest;
import model.user.User;
import utils.UserLogged;

import java.util.ArrayList;
import java.util.List;

public class OrdersRequestController {

    PlaceOrderController placeOrderController;
    SearchCafeteria searchCafeteriaController;
    User user;

    public OrdersRequestController() {
        placeOrderController = new PlaceOrderController();
        searchCafeteriaController = new SearchCafeteria();
        user = UserLogged.getInstance().getUser();
    }

    public List<OrderRequestBean> getAllRequest(){
        List<OrderRequest> orderReq;
        List<OrderRequestBean> retBeans = new ArrayList<>();

        orderReq = DAOfactory.getDAOfactory().createOrderRequestDAO().getAllOrderRequests();

        for(int i = 0; i < orderReq.size(); i++){
            if(orderReq.get(i).getStatus().equals("PENDING") && orderReq.get(i).getCafeteria().getName().equals(user.getCafeteria())){
                retBeans.add(placeOrderController.getOrdReqBean(orderReq.get(i)));
            }
        }

        return retBeans;
    }

    public OrderRequest getOrderReqFromBean(OrderRequestBean bean) {

        List<OrderRequest> orderReq = DAOfactory.getDAOfactory().createOrderRequestDAO().getAllOrderRequests();

        for (int i = 0; i < orderReq.size(); i++) {
            //il pickUpCode è generato randomicamente, la prob che ci siano più ordini con lo stesso pickUpCode appartenenti alla stessa caffetteria è praticamente zero
            if(orderReq.get(i).getPickUpCode().equals(bean.getCode()) && orderReq.get(i).getCafeteria().getName().equals(bean.getCafe())) {
                return orderReq.get(i);
            }
        }

        return null;
    }

    public void acceptRequest(OrderRequestBean bean) {
        OrderRequest order = getOrderReqFromBean(bean);
        order.setStatus("ACCEPTED");
    }

    public void rejectRequest(OrderRequestBean bean,String reason) {
        OrderRequest order = getOrderReqFromBean(bean);
        if(reason == null){
            order.setStatus("REJECTED");
        }
        else {
            order.setStatus("REJECTED: " + reason);
        }
    }

}
