package utils;


import model.DAOfactory;
import model.cafeteria.Cafeteria;
import java.util.List;

public class RetrieveCafeterias {

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

    //TODO ricerca per indirizzo/città con API


}
