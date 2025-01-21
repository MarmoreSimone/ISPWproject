package model.MenuItem;


import exception.NoCafeteriasFoundException;
import model.cafeteria.Cafeteria;

import java.util.List;

public abstract class MenuItemDAO {

        public abstract void saveItem(MenuItem bev, Cafeteria cafeteria);

        public abstract List<MenuItem> getAllItems(Cafeteria cafeteria);

        public abstract void saveItemOrderList(List<MenuItem> beverages, String cafeteria, String orderReq);

        public abstract List<MenuItem> getItemOrderList(String orderReq);
}

