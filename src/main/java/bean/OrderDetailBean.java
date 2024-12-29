package bean;

public class OrderDetailBean {

    private String payMethod;
    private String note;
    private String date;
    private String time;

    public OrderDetailBean() {}

    public OrderDetailBean(String payMethod, String note, String date, String time) {
        this.payMethod = payMethod;
        this.note = note;
        this.date = date;
        this.time = time;
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
