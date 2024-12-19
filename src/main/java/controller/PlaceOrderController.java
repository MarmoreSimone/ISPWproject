package controller;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import model.DAOfactory;
import model.cafeteria.Cafeteria;


import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController {

    public CafeteriaBean getCafeteriaByName(SearchCafeteriaBean key){
        List<Cafeteria> temp = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        for (Cafeteria cafe : temp) {
            if (cafe.getName().equals(key.getName())) {
                return new CafeteriaBean(cafe.getName(), cafe.getAddress());
            }
        }
        return null;
    }

    /*
    public CafeteriaBean getCafeteriaByAddress(SearchCafeteriaBean key){
        //da implementare

    }
    */

    public List<CafeteriaBean> getAllCafeterias(){

        //ELIMINA ROBA SOTTO

        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("BAR DI INGENIERIA","via del cambridge"));
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("bar di medicina","via del signore"));
        DAOfactory.getDAOfactory().createCafeteriaDAO().saveCafeteria(new Cafeteria("bar di lettere","via del cammino"));

        //

        List<Cafeteria> temp = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        List<CafeteriaBean> beans = new ArrayList<>();

        for(Cafeteria cafeteria : temp){
            beans.add(new CafeteriaBean(cafeteria.getName(), cafeteria.getAddress()));
        }

   return beans;
    }

}
