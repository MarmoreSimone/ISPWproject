package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOmem;

public class DAOfactoryFS extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in FS");
        return CafeteriaDAOmem.getInstance(); //metti fs
    }

}
