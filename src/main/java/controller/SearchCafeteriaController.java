package controller;

import bean.CafeteriaBean;
import bean.MapsAPIBean;
import bean.SearchCafeteriaBean;
import engineering.MapsApi;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchCafeteriaController {

    public List<CafeteriaBean> getAllCafeterias() throws NoCafeteriasFoundException, SystemErrorException {

        List<CafeteriaBean> cafeterias = new ArrayList<>();

        List<Cafeteria> cafeteriasList = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        for(int i = 0; i < cafeteriasList.size();i++){

            cafeterias.add(getCafeBean(cafeteriasList.get(i)));
        }

        return cafeterias;
    }

    public CafeteriaBean getCafeteriaByName(SearchCafeteriaBean name) throws NoCafeteriasFoundException, SystemErrorException {
        return getCafeBean(DAOfactory.getDAOfactory().createCafeteriaDAO().getCafeteriaByName(name.getName()));
    }

    private CafeteriaBean getCafeBean(Cafeteria cafe){
        return new CafeteriaBean(cafe.getName(), cafe.getAddress(), cafe.getCity(), cafe.getNumber(), cafe.getDescription(), cafe.getPhoto());
    }

    public List<CafeteriaBean> getCafeByAddress(SearchCafeteriaBean searchBean) throws NoCafeteriasFoundException, SystemErrorException {


        List<CafeteriaBean> returnCafes = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        CafeteriaBean temp;

        List<Cafeteria> cafeterias = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        for (int i = 0; i < cafeterias.size(); i++) {
            addresses.add(cafeterias.get(i).getAddress());
        }

        MapsApi api = new MapsApi();
        MapsAPIBean bean = new MapsAPIBean(searchBean.getAddress(),addresses);
        List<Long> distances = api.getDistances(bean);

        for(int i = 0; i<distances.size();i++){
            temp = getCafeBean(cafeterias.get(i));
            temp.setDistanceInMeters(distances.get(i));
            temp.setDistance(" " + Math.round(((float) distances.get(i) /1000)*100)/100.0 + "Km");
            returnCafes.add(temp);
        }

        //funzionalità offerta da java8
        returnCafes.sort(Comparator.comparingLong(CafeteriaBean::getDistanceInMeters));

        return returnCafes;


    }

}
