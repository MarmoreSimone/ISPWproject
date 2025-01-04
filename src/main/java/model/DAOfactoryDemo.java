package model;

import model.beverage.BeverageDAO;
import model.beverage.BeverageDAOdemo;
import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;
import model.order.OrderDAO;
import model.order.OrderDAOdemo;
import model.orderrequest.OrderRequestDAO;
import model.orderrequest.OrderRequestDAOdemo;
import model.user.UserDAO;
import model.user.UserDAOdemo;

public class DAOfactoryDemo extends DAOfactory {

    public CafeteriaDAO createCafeteriaDAO(){
        return CafeteriaDAOdemo.getInstance();
    }

    public OrderDAO createOrderDAO(){
        return OrderDAOdemo.getInstance();
    }

    public UserDAO createUserDAO() {
        return UserDAOdemo.getInstance();
    }

    public OrderRequestDAO createOrderRequestDAO() {
        return OrderRequestDAOdemo.getInstance();
    }

    public BeverageDAO createBeverageDAO() {
        return new BeverageDAOdemo();

    }
}
