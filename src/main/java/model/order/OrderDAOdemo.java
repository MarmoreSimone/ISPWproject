package model.order;



public class OrderDAOdemo extends OrderDAO {

    private static OrderDAOdemo instance = null;


    public static synchronized OrderDAOdemo getInstance() {
        if(OrderDAOdemo.instance == null)
            OrderDAOdemo.instance = new OrderDAOdemo();
        return OrderDAOdemo.instance;
    }


    public OrderDAOdemo() {}

    public void saveOrder(Order order) {
        System.out.println("save order");
    }



}
