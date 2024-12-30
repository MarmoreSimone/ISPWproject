package bean;

import java.util.List;

public class OrderBean extends OrderDetailBean {

    //più attributi presi da OrderDetailBean
    private List<BeverageBean> bevs;
    private double totPrice;


    public OrderBean() {

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
