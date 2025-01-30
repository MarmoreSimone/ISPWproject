package mytest;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import controller.SearchCafeteriaController;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSearchCafeteria {

    private static SearchCafeteriaController controller;

    @BeforeAll
    public static void setUp() throws SystemErrorException {

        //imposto factory DAO
        DAOfactory.setDAOfactory(Integer.parseInt("1"));
        controller = new SearchCafeteriaController();
        //imposto entity caffetteria
        Cafeteria cafeteria = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria("bar di ingegneria","via del politecnico, 1","Tor vergata","067259759","The Bar of Engineering at Tor Vergata University offers a variety of drinks, snacks, and quick meals. It's a casual spot where students and staff can relax between classes, grab a coffee, or have a bite to eat","/images/baring.jpg");
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(cafeteria);

    }
    @Test
    void testSearchCafeteriaByName() throws SystemErrorException, NoCafeteriasFoundException {
        SearchCafeteriaBean search = new SearchCafeteriaBean();
        search.setName("bar di ingegneria");

        CafeteriaBean cafeteria = controller.getCafeteriaByName(search);
        assertEquals("bar di ingegneria",cafeteria.getName());
    }

    @Test
    void testSearchCafeteriaFailed() throws SystemErrorException{

        SearchCafeteriaBean search = new SearchCafeteriaBean();
        search.setName("bar non esistente");

        int value = 0;

        try {
            controller.getCafeteriaByName(search);
        } catch (NoCafeteriasFoundException e) {
            value = 1;
        }

        assertEquals(1,value);
    }


}
