package mytest;

import bean.CustomOptionsBean;
import bean.MenuItemBean;
import controller.PlaceOrderController;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;
import model.menuitem.MenuItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItemCustomization {

    private static PlaceOrderController controller;
    private static MenuItem originalBeverage;
    private static Cafeteria cafeteria;
    private static MenuItem topping;

    @BeforeAll
    public static void setUp() throws SystemErrorException {


        //imposto factory DAO
        DAOfactory.setDAOfactory(Integer.parseInt("1"));
        controller = new PlaceOrderController();

        //imposto entity caffetteria con 2 bevande e un topping
        cafeteria = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del politecnico, 1","Tor vergata","067259759","The Bar of Engineering at Tor Vergata University offers a variety of drinks, snacks, and quick meals. It's a casual spot where students and staff can relax between classes, grab a coffee, or have a bite to eat","/images/baring.jpg");
        originalBeverage = new MenuItem("Americano", "Strong black coffee diluted with hot water", 2, 30, 95, "/images/americano.jpg", "beverage");
        topping = new MenuItem("whipped cream", null, 0.4, 50, 0, null, "topping");

        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(originalBeverage, cafeteria);
        DAOfactory.getDAOfactory().createMenuItemDAO().saveItem(topping, cafeteria);

    }

    @Test
    void testItemCustomizationPrice(){

        List<String> toppings = new ArrayList<>();
        toppings.add("whipped cream");
        CustomOptionsBean bean = new CustomOptionsBean(toppings,2,0,0);
        MenuItemBean customItem = controller.menuItemDecorator(bean,originalBeverage,cafeteria);

        assertEquals(originalBeverage.getPrice() + bean.getCoffeeShots()*0.3 + topping.getPrice(),customItem.getPrice());

    }

    @Test
    void testItemCustomizationCaffeine(){

        List<String> toppings = new ArrayList<>();
        toppings.add("whipped cream");
        CustomOptionsBean bean = new CustomOptionsBean(toppings,2,0,0);
        MenuItemBean customItem = controller.menuItemDecorator(bean,originalBeverage,cafeteria);

        assertEquals(originalBeverage.getCaffeine() + bean.getCoffeeShots()*50 + topping.getCaffeine(),customItem.getCaffeine());

    }



}
