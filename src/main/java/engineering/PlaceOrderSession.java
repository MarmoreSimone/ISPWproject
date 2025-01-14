package engineering;

import model.DAOfactory;
import model.beverage.Beverage;
import model.cafeteria.Cafeteria;
import model.order.Order;
import model.user.User;
import utils.UserLogged;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceOrderSession {

    public PlaceOrderSession() {

        myBeverages = new ArrayList<>();
        order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();
        user = UserLogged.getInstance().getUser();

    }

    private User user;
    private Cafeteria myCafeteria;
    private List<Beverage> myBeverages;
    private Order order;
    private Random random;

    public Cafeteria getMyCafeteria() {
        return myCafeteria;
    }

    public void setMyCafeteria(Cafeteria myCafeteria) {
        this.myCafeteria = myCafeteria;
    }

    public List<Beverage> getMyBeverages() {
        return myBeverages;
    }

    public void setMyBeverages(List<Beverage> myBeverages) {
        this.myBeverages = myBeverages;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
