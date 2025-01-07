package graphicalcontrollers.popup;

import model.DAOfactory;
import model.DAOfactoryDB;
import model.DAOfactoryDemo;
import model.DAOfactoryFS;
import model.beverage.BeverageDAO;
import model.cafeteria.CafeteriaDAO;
import model.order.OrderDAO;
import model.orderrequest.OrderRequestDAO;
import model.user.UserDAO;

public class PopupFactory {

    private static Popup me = null;

    protected PopupFactory() {
    }

    public static synchronized void setfactory(int scelta){
        if ( me == null ){

            switch (scelta) {
                case 1 :
                    me = new PopupGUI();
                    break;
                case 2 :
                    me = new PopupCLI();
                    break;

                default:
            }
        }
    }

    public static synchronized Popup getPopup(){
        return me;
    }


}
