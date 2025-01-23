package controller;

import bean.BeanUtils;
import bean.OrderRequestBean;
import engineering.SessionManager;
import exception.SystemErrorException;
import model.orderrequest.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public class UserOrdersController {

    public List<OrderRequestBean> getAllMyOrderReq() throws SystemErrorException {

        List<OrderRequestBean> retBeans = new ArrayList<>();
        List<OrderRequest> ordReq = SessionManager.getInstance().getUserClientLogged().getOrderRequestList();

        BeanUtils beanUtils = new BeanUtils();

        for(int i=0; i<ordReq.size(); i++){

            retBeans.add(beanUtils.getOrdReqBean(ordReq.get(i)));
        }

        return retBeans;

    }

    public List<OrderRequestBean> getAllMyOrderReq(String filter) throws SystemErrorException{
        List<OrderRequestBean> reqBeans = new ArrayList<>(getAllMyOrderReq());

        if(filter.charAt(0) == 'R'){
            for (int i = reqBeans.size() - 1; i >= 0; i--) {
                if (reqBeans.get(i).getState().equals("PENDING") || reqBeans.get(i).getState().equals("ACCEPTED")) {
                    reqBeans.remove(i);
                }
            }

        }else {

            for (int i = reqBeans.size() - 1; i >= 0; i--) {
                if (!reqBeans.get(i).getState().equals(filter)) {
                    reqBeans.remove(i);
                }
            }
        }
        return reqBeans;

    }


}
