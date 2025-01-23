package model.menuitem;


import model.cafeteria.Cafeteria;

import java.util.List;

public abstract class MenuItemDAO {

        public MenuItem createNewMenuItem(String name, String description, double price, double calories, double caffeine, String image, String type){
                return new MenuItem(name, description, price, calories, caffeine, image, type);
        }

        public abstract void saveItem(MenuItem bev, Cafeteria cafeteria);

        public abstract List<MenuItem> getAllItems(Cafeteria cafeteria);

        public abstract void saveItemOrderList(List<MenuItem> beverages, String cafeteria, String orderReq);

        public abstract List<MenuItem> getItemOrderList(String orderReq);
}

