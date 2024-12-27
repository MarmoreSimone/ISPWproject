package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;

public class DAOfactoryDB extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in DATABASE");
        return CafeteriaDAOdemo.getInstance(); //metti db
    }
}
