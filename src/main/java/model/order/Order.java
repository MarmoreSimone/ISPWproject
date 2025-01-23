package model.order;

import model.menuitem.MenuItem;

import java.util.List;

public class Order {

        private List<MenuItem> items;
        private double totPrice;
        private String payMethod;
        private String note;
        private String date;
        private String time;

        public Order() {

        }

        public Order(double totPrice, String payMethod, String note, String date, String time) {
                this.totPrice = totPrice;
                this.payMethod = payMethod;
                this.note = note;
                this.date = date;
                this.time = time;
        }

        public List<MenuItem> getBevs() {
                return items;
        }

        public void setItems(List<MenuItem> items) {
                this.items = items;
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






}
