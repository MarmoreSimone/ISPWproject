package model.cafeteria;

import exception.NoCafeteriasFoundException;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAOdemo extends CafeteriaDAO {

    private static CafeteriaDAOdemo instance = null;
    private List<Cafeteria> memory = new ArrayList<>();


    public static synchronized CafeteriaDAOdemo getInstance() {
        if(CafeteriaDAOdemo.instance == null)
            CafeteriaDAOdemo.instance = new CafeteriaDAOdemo();
        return CafeteriaDAOdemo.instance;
    }

    public void saveCafeteria(Cafeteria cafe) {
        memory.add(cafe);
    }

    public Cafeteria getCafeteriaByName(String name) throws NoCafeteriasFoundException {
        for (Cafeteria cafe : memory) {
            if (cafe.getName().equals(name)) {
                return cafe;
            }
        }

        throw new NoCafeteriasFoundException(": no cafeteria with this name found in the system");
    }


    public List <Cafeteria> getAllCafeterias() throws NoCafeteriasFoundException {
        if(memory.isEmpty()) {
            throw new NoCafeteriasFoundException(": no cafeterias registered in the system");
        }
        return memory;
    }


}
