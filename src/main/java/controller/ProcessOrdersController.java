package controller;

import bean.BeanUtils;
import bean.OrderRequestBean;
import engineering.SessionManager;
import exception.SystemErrorException;
import model.DAOfactory;
import model.orderrequest.OrderRequest;
import model.user.Barista;

import java.util.ArrayList;
import java.util.List;

public class ProcessOrdersController {

    Barista user;

    public ProcessOrdersController() {

        user = SessionManager.getInstance().getUserBaristaLogged();

    }

    //usato per mostrare tutte le richieste al barista
    public List<OrderRequestBean> getAllRequest() {
        List<OrderRequest> orderReq;
        List<OrderRequestBean> retBeans = new ArrayList<>();
        BeanUtils beanUtils = new BeanUtils();

        orderReq = user.getCafeteria().getOrderRequests();

        for (OrderRequest orderRequest : orderReq) {
            if (orderRequest.getStatus().equals("PENDING")) {
                retBeans.add(beanUtils.getOrdReqBean(orderRequest));
            }
        }

        return retBeans;
    }

    //usato quando il barista seleziona una specifica richiesta
    public OrderRequest getOrderReqFromBean(OrderRequestBean bean){

        List<OrderRequest> orderReq = user.getCafeteria().getOrderRequests();

        for (int i = 0; i < orderReq.size(); i++) {
            //il pickUpCode è generato randomicamente, la prob che ci siano più ordini con lo stesso pickUpCode appartenenti alla stessa caffetteria è praticamente zero
            if(orderReq.get(i).getPickUpCode().equals(bean.getCode())) {
                return orderReq.get(i);
            }
        }

        return null;
    }

    public void acceptRequest(OrderRequestBean bean) {
        OrderRequest order = getOrderReqFromBean(bean);
        DAOfactory.getDAOfactory().createOrderRequestDAO().changeStatus(order, "ACCEPTED");
    }

    public void rejectRequest(OrderRequestBean bean,String reason) {
        OrderRequest order = getOrderReqFromBean(bean);
        if(reason.equals("")){
            DAOfactory.getDAOfactory().createOrderRequestDAO().changeStatus(order, "REJECTED");
        }
        else {
            DAOfactory.getDAOfactory().createOrderRequestDAO().changeStatus(order, "REJECTED: " + reason);
        }
    }

}
