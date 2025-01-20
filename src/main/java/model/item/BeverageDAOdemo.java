package model.item;

import controller.SearchCafeteriaController;
import exception.NoCafeteriasFoundException;

import java.util.ArrayList;
import java.util.List;

public class BeverageDAOdemo extends BeverageDAO {
    //non la faccio singlenton in quanto è la entity della caffetteria che si occupa di mantenere i riferimenti alle bevande

    @Override
    public void saveBev(Beverage bev, String cafeteria) throws NoCafeteriasFoundException {
        //uso il controller applicativo che si occupa della parte di ricerca di una caffetteria per recuperare l'istanza della caffetteria
        SearchCafeteriaController search = new SearchCafeteriaController();

        search.getCafeteriaByName(cafeteria).setBeverages(bev);
    }

    @Override
    public List<Beverage> getAllBevs(String cafeteria) throws NoCafeteriasFoundException {
            SearchCafeteriaController search = new SearchCafeteriaController();
            return search.getCafeteriaByName(cafeteria).getBeverages();

    }

    //non dovrebbe essere mai usata in caso DEMO
    public void saveBevOrderList(List<Beverage> beverages, String cafeteria, String orderReq){
        System.out.println("there might be an error");
    }

    //non dovrebbe essere mai usata in caso DEMO
    public List<Beverage> getBevOrderList(String orderReq){
        List<Beverage> emptyList = new ArrayList<>();
        System.out.println("there might be an error");
        return emptyList;
    }

}
