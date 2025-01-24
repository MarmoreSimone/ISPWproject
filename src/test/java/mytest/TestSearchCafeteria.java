package mytest;

import controller.SearchCafeteriaController;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSearchCafeteria {

    private static SearchCafeteriaController controller;
    private static Cafeteria cafeteria;

    @BeforeAll
    public static void setUp() throws SystemErrorException {

        //imposto factory DAO
        DAOfactory.setDAOfactory(Integer.parseInt("1"));
        controller = new SearchCafeteriaController();
        //imposto entity caffetteria
        cafeteria = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del politecnico, 1","Tor vergata","067259759","The Bar of Engineering at Tor Vergata University offers a variety of drinks, snacks, and quick meals. It's a casual spot where students and staff can relax between classes, grab a coffee, or have a bite to eat","/images/baring.jpg");
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(cafeteria);

    }
    @Test
    void testSearchCafeteria() throws SystemErrorException, NoCafeteriasFoundException {
        Cafeteria cafeteria = controller.getCafeteriaByName("bar di ingegneria");
        assertNotNull(cafeteria);
    }

    @Test
    void testSearchCafeteriaFailed() throws SystemErrorException{

        int value = 0;

        try {
            Cafeteria cafeteria = controller.getCafeteriaByName("qqqqqq");
        } catch (NoCafeteriasFoundException e) {
            value = 1;
        }

        assertEquals(1,value);
    }


}
