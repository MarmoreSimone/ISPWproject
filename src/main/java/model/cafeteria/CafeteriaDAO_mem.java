package model.cafeteria;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAO_mem extends CafeteriaDAO {

    private static CafeteriaDAO_mem instance = null;
    private List<Cafeteria> memoria = new ArrayList<>();


    public void saveCafeteria(Cafeteria cafe) {
        memoria.add(cafe);
    }

    public Cafeteria getCafeteriaByName(String name) {
        for (Cafeteria cafe : memoria) {
            if (cafe.getName() == name) {
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

    public synchronized static CafeteriaDAO_mem getInstance() {
        if(CafeteriaDAO_mem.instance == null)
            CafeteriaDAO_mem.instance = new CafeteriaDAO_mem();
        return CafeteriaDAO_mem.instance;
    }

}
