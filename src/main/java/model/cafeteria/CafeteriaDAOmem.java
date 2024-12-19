package model.cafeteria;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAOmem extends CafeteriaDAO {

    private static CafeteriaDAOmem instance = null;
    private List<Cafeteria> memoria = new ArrayList<>();

    public static synchronized CafeteriaDAOmem getInstance() {
        if(CafeteriaDAOmem.instance == null)
            CafeteriaDAOmem.instance = new CafeteriaDAOmem();
        return CafeteriaDAOmem.instance;
    }

    public void saveCafeteria(Cafeteria cafe) {
        memoria.add(cafe);
    }

    public Cafeteria getCafeteriaByName(String name) {
        for (Cafeteria cafe : memoria) {
            if (cafe.getName().equals(name)) {
                return cafe;
            }
        }
        return null; //gestisci caso in cui non trova niente
    }

    public Cafeteria getCafeteriasByAddress(String address) {
        return null;
    }

    public List <Cafeteria> getAllCafeterias() {
        return memoria;
    }



}
