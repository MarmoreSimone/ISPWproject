package model.menuitem;


import exception.SystemErrorException;
import model.cafeteria.Cafeteria;

import java.util.List;

public abstract class MenuItemDAO {

        public MenuItem createNewMenuItem(String name, String description, double price, double calories, double caffeine, String image, String type){
                return new MenuItem(name, description, price, calories, caffeine, image, type);
        }

        public abstract void saveItem(MenuItem bev, Cafeteria cafeteria) throws SystemErrorException;

        public abstract List<MenuItem> getAllItems(Cafeteria cafeteria) throws SystemErrorException;

        public abstract void saveItemOrderList(List<MenuItem> beverages, String cafeteria, String orderReq) throws SystemErrorException;

        public abstract List<MenuItem> getItemOrderList(String orderReq) throws SystemErrorException;
}

