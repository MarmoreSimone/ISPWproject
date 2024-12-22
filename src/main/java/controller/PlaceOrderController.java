package controller;

import bean.CafeteriaBean;
import bean.SearchCafeteriaBean;
import model.cafeteria.Cafeteria;
import utils.RetrieveCafeterias;


import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController {

    private Cafeteria myCafe;

    //ritorna una lista di cafeteriaBean andando a filtrare la searchCafeteriaBean
    //uso la searchBean in questo passaggio in quanto non mi interessa mostrare tutte le informazioni della caffetteria nella ricerca
    public List<SearchCafeteriaBean> searchCafeterias(SearchCafeteriaBean key){

        RetrieveCafeterias searcher = new RetrieveCafeterias();

        //lista di searchCafeteriaBean da tornare
        List<SearchCafeteriaBean> foundCafes = new ArrayList<>();

        //caso in cui si vuole cercare per nome
        if(key.getName() != null){
            Cafeteria temp = searcher.getCafeteriaByName(key.getName());
            foundCafes.add(new SearchCafeteriaBean(temp.getName(), temp.getAddress()));
        }

        //caso in cui si vogliono cercare tutte le caffetterie disponibili
        else if(key.getAddress() == null && key.getName() == null){
            for(Cafeteria cafe: searcher.getAllCafeterias()){
                foundCafes.add(new SearchCafeteriaBean(cafe.getName(), cafe.getAddress()));
            }
        }

        //caso in cui si vuole cercare per indirizzo o città
        //TODO ricerca con api google maps

        return foundCafes;
    }

    public CafeteriaBean loadSelectedCafeteria(SearchCafeteriaBean key){

        RetrieveCafeterias searcher = new RetrieveCafeterias();
        Cafeteria tempCafe = searcher.getCafeteriaByName(key.getName());

        return new CafeteriaBean(tempCafe.getName(), tempCafe.getAddress(), tempCafe.getCity(), tempCafe.getNumber(), tempCafe.getDescription());

    }



}
