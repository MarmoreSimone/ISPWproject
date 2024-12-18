package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAO_mem;

public class DAOfactoryFS extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in FS");
        return CafeteriaDAO_mem.getInstance(); //metti fs
    }

}
