package model;

import model.cafeteria.CafeteriaDAO;

public abstract class DAOfactory {

    public static final String MEMORIA_CENTRALE = "mem";
    public static final String DATABASE = "db";
    public static final String FILE_SYSTEM = "fs";


    private static DAOfactory me = null;

    protected DAOfactory(){
    }

    public static synchronized DAOfactory setDAOfactory(int scelta){
        if ( me == null ){

            switch (scelta) {
                case 1 :
                    me = new DAOfactoryDemo();
                    break;
                case 2 :
                    me = new DAOfactoryDB();
                    break;
                case 3 :
                    me = new DAOfactoryFS();
                    break;
                default:
            }
        }
        return me;
    }

    public static synchronized DAOfactory getDAOfactory(){
        return me;
    }

    public abstract CafeteriaDAO createCafeteriaDAO();

}
