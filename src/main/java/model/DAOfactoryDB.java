package model;

import model.menuitem.MenuItemDAO;
import model.menuitem.MenuItemDAOdb;
import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdb;
import model.order.OrderDAO;
import model.order.OrderDAOdb;
import model.orderrequest.OrderRequestDAO;
import model.orderrequest.OrderRequestDAOdb;
import model.user.UserDAO;
import model.user.UserDAOdb;

public class DAOfactoryDB extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){
        return new CafeteriaDAOdb();
    }

    public OrderDAO createOrderDAO(){
        return new OrderDAOdb();
    }

    public UserDAO createUserDAO() {
        return new UserDAOdb();
    }

    public OrderRequestDAO createOrderRequestDAO() {
            return new OrderRequestDAOdb();
    }

    public MenuItemDAO createMenuItemDAO() {
        return new MenuItemDAOdb();
    }
}
