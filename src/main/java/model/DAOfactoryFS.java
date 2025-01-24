package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOfs;
import model.menuitem.MenuItemDAO;
import model.order.OrderDAO;
import model.orderrequest.OrderRequestDAO;
import model.user.UserDAO;
import model.user.UserDAOfs;

public class DAOfactoryFS extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){
        return new CafeteriaDAOfs();
    }

    public OrderDAO createOrderDAO(){

        return null;
    }

    public UserDAO createUserDAO(){
        return new UserDAOfs();
    }

    public OrderRequestDAO createOrderRequestDAO() {
        return null;
    }

    public MenuItemDAO createMenuItemDAO() {
        return null;
    }
}
