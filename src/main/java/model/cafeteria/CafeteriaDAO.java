package model.cafeteria;

import exception.NoCafeteriasFoundException;

import java.util.List;

public abstract class CafeteriaDAO {

    public abstract void saveCafeteria(Cafeteria cafe);

    public abstract Cafeteria getCafeteriaByName(String name) throws NoCafeteriasFoundException;

    public abstract List <Cafeteria> getAllCafeterias() throws NoCafeteriasFoundException;

    public Cafeteria createCafeteria(String name, String address, String city, String number, String description, String photo) {
        return new Cafeteria(name, address, city, number, description, photo);
    }

}
