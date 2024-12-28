package model.order;

import model.beverage.Beverage;
import model.cafeteria.Cafeteria;

import java.util.List;

public class Order {

        private List<Beverage> items;
        private Cafeteria cafeteria;
        private double totPrice;
        private String payMethod;
        private String note;
        private String status;
        private String pickUpCode;
        private String date;
        private String time;

        public Order() {

        }

        public Order(List<Beverage> items, Cafeteria cafeteria, double totPrice, String payMethod, String note, String date, String time) {
                this.items = items;
                this.cafeteria = cafeteria;
                this.totPrice = totPrice;
                this.payMethod = payMethod;
                this.note = note;
                this.date = date;
                this.time = time;
        }

        public List<Beverage> getBevs() {
                return items;
        }

        public void setItems(List<Beverage> items) {
                this.items = items;
        }

        public Cafeteria getCafeteria() {
                return cafeteria;
        }

        public void setCafeteria(Cafeteria cafeteria) {
                this.cafeteria = cafeteria;
        }

        public double getTotPrice() {
                return totPrice;
        }

        public void setTotPrice(double totPrice) {
                this.totPrice = totPrice;
        }

        public String getPayMethod() {
                return payMethod;
        }

        public void setPayMethod(String payMethod) {
                this.payMethod = payMethod;

        }

        public String getNote() {
                return note;
        }

        public void setNote(String note) {
                this.note = note;

        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
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
