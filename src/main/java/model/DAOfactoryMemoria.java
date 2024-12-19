package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOmem;

public class DAOfactoryMemoria extends DAOfactory {

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in memoria");
        return CafeteriaDAOmem.getInstance();
    }
}
