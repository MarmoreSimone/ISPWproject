package model.user;

import model.orderrequest.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{

        List<OrderRequest> orderRequestList;

        public Client(){

        }

        public Client(String username, String password, String role) {
            super(username, password, role);
            orderRequestList = new ArrayList<>();
        }


        public List<OrderRequest> getOrderRequestList() {
            return orderRequestList;
        }

        public void setOrderRequestList(List<OrderRequest> orderRequestList) {
            this.orderRequestList = orderRequestList;
        }




}
