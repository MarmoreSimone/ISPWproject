package model;

import model.cafeteria.CafeteriaDAO;
import model.cafeteria.CafeteriaDAO_mem;

public class DAOfactoryDB extends DAOfactory{

    public CafeteriaDAO createCafeteriaDAO(){

        return CafeteriaDAO_mem.getInstance(); //metti db
    }
}
