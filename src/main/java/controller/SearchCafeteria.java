package controller;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import model.DAOfactory;
import model.cafeteria.Cafeteria;


import java.util.ArrayList;
import java.util.List;

public class SearchCafeteria {

    public SearchCafeteria() {

    }

    public List<Cafeteria> getAllCafeterias(){
        return DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();
    }

    public Cafeteria getCafeteriaByName(String name){

        List<Cafeteria> cafeteriasInMem = getAllCafeterias();
        for (Cafeteria cafe : cafeteriasInMem) {
            if (cafe.getName().equals(name)) {
                return cafe;
            }
        }
        return null;
    }

    public List<SearchCafeteriaBean> searchCafeterias(SearchCafeteriaBean key){


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
        //TODO ricerca con api google maps

        return foundCafes;
    }

    public CafeteriaBean loadSelectedCafeteria(SearchCafeteriaBean key){

        Cafeteria tempCafe = getCafeteriaByName(key.getName());
        return new CafeteriaBean(tempCafe.getName(), tempCafe.getAddress(), tempCafe.getCity(), tempCafe.getNumber(), tempCafe.getDescription(), tempCafe.getOpeningHours(), tempCafe.getPhoto());

    }


}
