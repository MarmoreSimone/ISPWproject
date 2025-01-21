package model.MenuItem;

import controller.SearchCafeteriaController;
import exception.NoCafeteriasFoundException;
import model.cafeteria.Cafeteria;

import java.util.ArrayList;
import java.util.List;

public class MenuItemDAOdemo extends MenuItemDAO {
    //non la faccio singlenton in quanto è la entity della caffetteria che si occupa di mantenere i riferimenti alle bevande

    @Override
    public void saveItem(MenuItem item, Cafeteria cafeteria) {

        if(item.getType().equals("beverage"))
            cafeteria.setBeverages(item);
        else
            cafeteria.setTopping(item);
    }

    @Override
    public List<MenuItem> getAllItems(Cafeteria cafeteria) {
            return cafeteria.getBeverages();

    }

    //non dovrebbe essere mai usata in caso DEMO
    public void saveItemOrderList(List<MenuItem> beverages, String cafeteria, String orderReq){
        System.out.println("there might be an error");
    }

    //non dovrebbe essere mai usata in caso DEMO
    public List<MenuItem> getItemOrderList(String orderReq){
        List<MenuItem> emptyList = new ArrayList<>();
        System.out.println("there might be an error");
        return emptyList;
    }

}
