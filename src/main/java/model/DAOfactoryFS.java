package model;

import model.beverage.BeverageDAO;
import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;
import model.order.OrderDAO;
import model.order.OrderDAOdemo;
import model.orderrequest.OrderRequestDAO;
import model.user.UserDAO;

public class DAOfactoryFS extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){
        System.out.println("siamo in FS");
        return CafeteriaDAOdemo.getInstance(); //metti fs
    }

    public OrderDAO createOrderDAO(){

        return OrderDAOdemo.getInstance();
    }

    public UserDAO createUserDAO(){
        return null;
    }

    @Override
    public OrderRequestDAO createOrderRequestDAO() {
        return null;
    }

    public BeverageDAO createBeverageDAO() {
        return null;
    }
}
