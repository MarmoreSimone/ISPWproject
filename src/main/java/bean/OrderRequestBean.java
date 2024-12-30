package bean;

public class OrderRequestBean {

    private String username;
    private String cafe;
    private OrderBean order;
    private String state;
    private String code;

    public OrderRequestBean() {
    }

    public OrderRequestBean(String user, String cafeteria, OrderBean order, String state, String code) {
        this.username = user;
        this.cafe = cafeteria;
        this.order = order;
        this.state = state;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }
    public void setUser(String user) {
        this.username = user;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafeteria) {
        this.cafe = cafeteria;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String status) {
        this.state = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
