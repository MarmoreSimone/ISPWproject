package engineering;

import model.DAOfactory;
import model.MenuItem.MenuItem;
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
    private List<MenuItem> myBeverages;
    private Order order;
    private MenuItem customBev;


    public Cafeteria getMyCafeteria() {
        return myCafeteria;
    }

    public void setMyCafeteria(Cafeteria myCafeteria) {
        this.myCafeteria = myCafeteria;
    }

    public List<MenuItem> getMyBeverages() {
        return myBeverages;
    }

    public void setMyBeverages(List<MenuItem> myBeverages) {
        this.myBeverages = myBeverages;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getCustomBev() {
        return customBev;
    }

    public void setCustomBev(MenuItem customBev) {
        this.customBev = customBev;
    }



}
