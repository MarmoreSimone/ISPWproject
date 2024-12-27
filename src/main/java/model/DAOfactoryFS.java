package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAOdemo;

public class DAOfactoryFS extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        System.out.println("siamo in FS");
        return CafeteriaDAOdemo.getInstance(); //metti fs
    }

}
