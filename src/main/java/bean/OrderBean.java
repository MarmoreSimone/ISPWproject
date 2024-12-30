package bean;

import java.util.List;

public class OrderBean extends OrderDetailBean {

    //più attributi presi da OrderDetailBean
    private List<BeverageBean> bevs;
    private double totPrice;



    public OrderBean() {

    }

    public OrderBean(List<BeverageBean> items, CafeteriaBean cafeteria, double totPrice, String pickUpCode,String payMethod, String note, String date, String time) {
        super(payMethod, note, date, time);
        this.bevs = items;
        this.totPrice = totPrice;
    }

    public List<BeverageBean> getBevs() {
        return bevs;
    }

    public void setBevs(List<BeverageBean> bevs) {
        this.bevs = bevs;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }









}
