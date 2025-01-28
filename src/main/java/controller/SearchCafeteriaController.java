package controller;

import bean.CafeteriaBean;
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

    public CafeteriaBean getCafeteriaByName(String name) throws NoCafeteriasFoundException, SystemErrorException {
        return getCafeBean(DAOfactory.getDAOfactory().createCafeteriaDAO().getCafeteriaByName(name));
    }

    private CafeteriaBean getCafeBean(Cafeteria cafe){
        return new CafeteriaBean(cafe.getName(), cafe.getAddress(), cafe.getCity(), cafe.getNumber(), cafe.getDescription(), cafe.getPhoto());
    }

    public List<CafeteriaBean> getCafeByAddress(String address) throws NoCafeteriasFoundException, SystemErrorException {

        List<CafeteriaBean> returnCafes = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        CafeteriaBean temp;

        List<Cafeteria> cafeterias = DAOfactory.getDAOfactory().createCafeteriaDAO().getAllCafeterias();

        for (int i = 0; i < cafeterias.size(); i++) {
            addresses.add(cafeterias.get(i).getAddress());
        }

        MapsApi api = new MapsApi();
        List<Long> distances = api.getDistances(address,addresses);

        for(int i = 0; i<distances.size();i++){
            temp = getCafeBean(cafeterias.get(i));
            temp.setDistanceInMeters(distances.get(i));
            temp.setDistance(" " + Math.round(((float) distances.get(i) /1000)*100)/100.0 + "Km");
            returnCafes.add(temp);
        }


        returnCafes.sort(Comparator.comparingLong(CafeteriaBean::getDistanceInMeters));

        return returnCafes;


    }

}
