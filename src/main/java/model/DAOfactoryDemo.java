package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;

public class DAOfactoryDemo extends DAOfactory {

    public CafeteriaDAO createCafeteriaDAO(){

        //System.out.println("siamo in memoria");
        return CafeteriaDAOdemo.getInstance();
    }
}
