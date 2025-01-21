package bean;

import java.util.List;

public class OrderBean extends OrderDetailBean {

    //più attributi presi da OrderDetailBean
    private List<MenuItemBean> bevs;
    private double totCost;


    public List<MenuItemBean> getBevs() {
        return bevs;
    }

    public void setBevs(List<MenuItemBean> bevs) {
        this.bevs = bevs;
    }

    public double getTotPrice() {
        return totCost;
    }

    public void setTotPrice(double totPrice) {
        this.totCost = totPrice;
    }









}
