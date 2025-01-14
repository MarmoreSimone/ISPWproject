package model.order;



public class OrderDAOdemo extends OrderDAO {

    private static OrderDAOdemo instance = null;

    public static synchronized OrderDAOdemo getInstance() {
        if(OrderDAOdemo.instance == null)
            OrderDAOdemo.instance = new OrderDAOdemo();
        return OrderDAOdemo.instance;
    }


    protected OrderDAOdemo() {

    }

    //nel caso DEMO il riferimento all'istanza viene salvato nella entity order
    public void saveOrder(Order order, String orderReq, String cafeteria) {
        System.out.println("there might be a problem");
    }


    public Order getOrderByOrderReq(String orderReq, String cafeteria){
        System.out.println("there might be a problem");
        return null;
    }



}
