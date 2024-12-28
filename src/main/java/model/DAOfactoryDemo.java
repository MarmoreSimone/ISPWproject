package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;
import model.order.OrderDAO;
import model.order.OrderDAOdemo;

public class DAOfactoryDemo extends DAOfactory {

    public CafeteriaDAO createCafeteriaDAO(){
        return CafeteriaDAOdemo.getInstance();
    }

    public OrderDAO createOrderDAO(){
        return OrderDAOdemo.getInstance();
    }
    
}
