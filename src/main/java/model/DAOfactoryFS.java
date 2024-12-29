package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;
import model.order.OrderDAO;
import model.order.OrderDAOdemo;
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

}
