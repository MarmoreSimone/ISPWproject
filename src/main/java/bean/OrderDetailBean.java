package bean;

public class OrderDetailBean {

    private String paymentMethod;
    private String message;
    private String pickDate;
    private String pickTime;

    public OrderDetailBean() {}

    public OrderDetailBean(String payMethod, String note, String date, String time) {
        this.paymentMethod = payMethod;
        this.message = note;
        this.pickDate = date;
        this.pickTime = time;
    }

    public String getPayMethod() {
        return paymentMethod;
    }

    public void setPayMethod(String payMethod) {
        this.paymentMethod = payMethod;
    }

    public String getNote() {
        return message;
    }

    public void setNote(String note) {
        this.message= note;
    }

    public String getDate() {
        return pickDate;
    }

    public void setDate(String date) {
        this.pickDate = date;
    }

    public String getTime() {
        return pickTime;
    }

    public void setTime(String time) {
        this.pickTime = time;
    }


}
