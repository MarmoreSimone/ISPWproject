package model.beverage;

import controller.SearchCafeteria;
import model.DAOfactory;
import model.cafeteria.Cafeteria;
import model.user.User;
import utils.UserLogged;

import java.util.List;

public class BeverageDAOdemo extends BeverageDAO {
    //non la faccio singlenton in quanto è la entity della caffetteria che si occupa di mantenere i riferimenti alle bevande

    @Override
    public void saveBev(Beverage bev, String cafeteria) {
        //uso il controller applicativo che si occupa della parte di ricerca di una caffetteria per recuperare l'istanza della caffetteria
        SearchCafeteria search = new SearchCafeteria();

        search.getCafeteriaByName(cafeteria).setBeverages(bev);
    }

    @Override
    public List<Beverage> getAllBevs(String cafeteria) {
            SearchCafeteria search = new SearchCafeteria();
            return search.getCafeteriaByName(cafeteria).getBeverages();

    }

    public void saveBevOrderList(List<Beverage> beverages, String cafeteria, String orderReq){

    }

    public List<Beverage> getBevOrderList(String orderReq){

        return null;
    }

}
