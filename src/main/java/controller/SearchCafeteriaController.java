package controller;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import exception.NoCafeteriasFoundException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;


import java.util.ArrayList;
import java.util.List;

public class SearchCafeteriaController {

    public List<Cafeteria> getAllCafeterias() throws NoCafeteriasFoundException {

        List<Cafeteria> cafeterias ;

        cafeterias = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        return cafeterias;
    }

    public Cafeteria getCafeteriaByName(String name) throws NoCafeteriasFoundException {

         return DAOfactory.getDAOfactory().createCafeteriaDAO().getCafeteriaByName(name);


    }

    //usata quando non devo passare tutte le informazioni della caffetteria
    public List<SearchCafeteriaBean> searchCafeterias(SearchCafeteriaBean key) throws NoCafeteriasFoundException {

        //lista di searchCafeteriaBean da tornare
        List<SearchCafeteriaBean> foundCafes = new ArrayList<>();

        //caso in cui si vuole cercare per nome
        if(key.getName() != null){
            Cafeteria temp = getCafeteriaByName(key.getName());
            foundCafes.add(new SearchCafeteriaBean(temp.getName(), temp.getAddress()));
        }

        //caso in cui si vogliono cercare tutte le caffetterie disponibili
        else if(key.getAddress() == null && key.getName() == null){
            for(Cafeteria cafe: getAllCafeterias()){
                foundCafes.add(new SearchCafeteriaBean(cafe.getName(), cafe.getAddress()));
            }
        }

        //caso in cui si vuole cercare per indirizzo o città
        //ricerca con api google maps

        return foundCafes;
    }

    //usata per tornare la bean che contiene tutte le informazioni della caffetteria
    public CafeteriaBean getCafeBeanByName(String name) throws NoCafeteriasFoundException {

        Cafeteria tempCafe = getCafeteriaByName(name);

        return getCafeBean(tempCafe);

    }

    private CafeteriaBean getCafeBean(Cafeteria cafe){
        return new CafeteriaBean(cafe.getName(), cafe.getAddress(), cafe.getCity(), cafe.getNumber(), cafe.getDescription(), cafe.getPhoto());

    }


}
