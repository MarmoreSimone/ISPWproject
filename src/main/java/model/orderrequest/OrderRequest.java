package model.orderrequest;

import model.cafeteria.Cafeteria;
import model.order.Order;

public class OrderRequest {

        private String user;
        private Cafeteria cafeteria;
        private Order order;
        private String status;
        private String pickUpCode;

        public OrderRequest() {
        }



        public OrderRequest(String user, Cafeteria cafeteria, String status, String pickUpCode) {
            this.user = user;
            this.cafeteria = cafeteria;
            this.status = status;
            this.pickUpCode = pickUpCode;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Cafeteria getCafeteria() {
            return cafeteria;
        }

        public void setCafeteria(Cafeteria cafeteria) {
            this.cafeteria = cafeteria;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPickUpCode() {
            return pickUpCode;
        }

        public void setPickUpCode(String pickUpCode) {
            this.pickUpCode = pickUpCode;
        }
}
