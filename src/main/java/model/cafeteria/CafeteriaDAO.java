package model.cafeteria;

import java.util.List;

public abstract class CafeteriaDAO {

    private static CafeteriaDAO_mem instance;
    private List<Cafeteria> memoria;


    public abstract void saveCafeteria(Cafeteria cafe);

    public abstract Cafeteria getCafeteriaByName(String name);

    public abstract Cafeteria getCafeteriasByAddress(String address);

    public abstract List <Cafeteria> getAllCafeterias();

}
