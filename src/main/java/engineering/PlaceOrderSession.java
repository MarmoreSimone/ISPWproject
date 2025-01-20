package engineering;

import model.DAOfactory;
import model.item.Beverage;
import model.cafeteria.Cafeteria;
import model.order.Order;
import java.util.ArrayList;
import java.util.List;


public class PlaceOrderSession {

    public PlaceOrderSession() {

        myBeverages = new ArrayList<>();
        order = DAOfactory.getDAOfactory().createOrderDAO().createNewOrder();

    }


    private Cafeteria myCafeteria;
    private List<Beverage> myBeverages;
    private Order order;
    private Beverage customBev;


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

    public Beverage getCustomBev() {
        return customBev;
    }

    public void setCustomBev(Beverage customBev) {
        this.customBev = customBev;
    }



}
