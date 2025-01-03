package model.cafeteria;

import java.util.List;

public abstract class CafeteriaDAO {

    public abstract void saveCafeteria(Cafeteria cafe);

    //public abstract Cafeteria getCafeteriaByName(String name);

    public abstract Cafeteria getCafeteriasByAddress(String address);

    public abstract List <Cafeteria> getAllCafeterias();

    public Cafeteria createCafeteria(String name, String address, String city, String number, String description, String photo) {
        return new Cafeteria(name, address, city, number, description, photo);
    }

}
