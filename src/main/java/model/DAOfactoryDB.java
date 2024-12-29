package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;
import model.order.OrderDAO;
import model.order.OrderDAOdemo;
import model.user.UserDAO;

public class DAOfactoryDB extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in DATABASE");
        return CafeteriaDAOdemo.getInstance(); //metti db
    }

    public OrderDAO createOrderDAO(){
        System.out.println("siamo in DATABASE");
        return OrderDAOdemo.getInstance();
    }

    public UserDAO createUserDAO() {
        return null;
    }
}
