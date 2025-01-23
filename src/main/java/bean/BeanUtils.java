package bean;

import model.menuitem.MenuItem;
import model.order.Order;
import model.orderrequest.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

            //data una order request torna la rispettiva bean
            public OrderRequestBean getOrdReqBean(OrderRequest ord){
                OrderRequestBean bean = new OrderRequestBean();

                if(ord.getCafeteria() != null) bean.setCafe(ord.getCafeteria().getName());
                bean.setCode(ord.getPickUpCode());
                bean.setState(ord.getStatus());
                bean.setOrder(getOrderBean(ord.getOrder()));
                bean.setUser(ord.getUser());

                return bean;

            }

            //dato l'order torna la bean
            public OrderBean getOrderBean(Order ord){

                OrderBean bean = new OrderBean();
                bean.setBevs(getBeveragesBeanList(ord.getBevs()));
                bean.setDate(ord.getDate());
                bean.setTime(ord.getTime());
                bean.setNote(ord.getNote());
                bean.setTotPrice(ord.getTotPrice());
                bean.setPayMethod(ord.getPayMethod());
                return bean;
            }

            //data un lista di bevande torna la corrispettiva lista di bean bevande
            public List<MenuItemBean> getBeveragesBeanList(List<MenuItem> beverages){
                List<MenuItemBean> retBeans = new ArrayList<>();

                for(MenuItem bev: beverages){
                    retBeans.add(new MenuItemBean(bev.getName(), bev.getDescription(), bev.getPrice(), bev.getCalories(), bev.getCaffeine(), bev.getImage(), bev.getType()));
                }

                return retBeans;

            }

}
