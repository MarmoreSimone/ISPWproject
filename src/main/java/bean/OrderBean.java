package bean;

import java.util.List;

public class OrderBean extends OrderDetailBean {

    //più attributi presi da OrderDetailBean
    private List<BeverageBean> bevs;
    private CafeteriaBean cafeteria;
    private double totPrice;
    private String pickUpCode;

    public OrderBean(List<BeverageBean> items, CafeteriaBean cafeteria, double totPrice, String pickUpCode,String payMethod, String note, String date, String time) {

        super(payMethod, note, date, time);
        this.bevs = items;
        this.cafeteria = cafeteria;
        this.totPrice = totPrice;
        this.pickUpCode = pickUpCode;

    }

    public List<BeverageBean> getBevs() {
        return bevs;
    }

    public void setBevs(List<BeverageBean> bevs) {
        this.bevs = bevs;
    }

    public CafeteriaBean getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(CafeteriaBean cafeteria) {
        this.cafeteria = cafeteria;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }

    public String getPickUpCode() {
        return pickUpCode;
    }

    public void setPickUpCode(String pickUpCode) {
        this.pickUpCode = pickUpCode;
    }




}
