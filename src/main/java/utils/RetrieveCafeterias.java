package utils;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import model.DAOfactory;
import model.cafeteria.Cafeteria;

import java.util.ArrayList;
import java.util.List;

public class RetrieveCafeterias {

    private List<Cafeteria> cafeteriasInMem = new ArrayList<>();

    public Cafeteria getCafeteriaByName(String name){
        //recupero le Cafeteria dalla DAO
        cafeteriasInMem = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        for (Cafeteria cafe : cafeteriasInMem) {
            if (cafe.getName().equals(name)) {
                return cafe;
            }
        }

        return null;
    }

    /*
    public CafeteriaBean getCafeteriaByAddress(SearchCafeteriaBean key){
        //da implementare
    }
    */

    public List<Cafeteria> getAllCafeterias(){
        return DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();
    }
}
