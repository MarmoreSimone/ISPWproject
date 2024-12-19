package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOmem;

public class DAOfactoryDB extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in DATABASE");
        return CafeteriaDAOmem.getInstance(); //metti db
    }
}
