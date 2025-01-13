package model.beverage;


import exception.NoCafeteriasFoundException;

import java.util.List;

public abstract class BeverageDAO {

        public abstract void saveBev(Beverage bev, String cafeteria) throws NoCafeteriasFoundException;

        public abstract List<Beverage> getAllBevs(String cafeteria) throws NoCafeteriasFoundException;

        public abstract void saveBevOrderList(List<Beverage> beverages, String cafeteria, String orderReq);

        public abstract List<Beverage> getBevOrderList(String orderReq);
}
