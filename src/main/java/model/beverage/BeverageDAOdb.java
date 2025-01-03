package model.beverage;

import model.cafeteria.Cafeteria;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeverageDAOdb extends BeverageDAO {

    @Override
    public void saveBev(Beverage bev, String cafeteria) {

        String query = "INSERT INTO beverage (name, description, price, calories, caffeine, image, cafeteria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bev.getName());
            ps.setString(2, bev.getDescription());
            ps.setDouble(3, bev.getPrice());
            ps.setDouble(4, bev.getCalories());
            ps.setDouble(5, bev.getCaffeine());
            ps.setString(6, bev.getImage());
            ps.setString(7, cafeteria);

            // Esegui la query
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<Beverage> getAllBevs(String cafeteria) {
        List<Beverage> list = new ArrayList<>();
        String query = "SELECT name, description, price, calories, caffeine, image FROM beverage WHERE cafeteria = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, cafeteria);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String descr = rs.getString("description");
                Double price = rs.getDouble("price");
                Double calories = rs.getDouble("calories");
                Double caffeine = rs.getDouble("caffeine");
                String image = rs.getString("image");
                Beverage bev = new Beverage(name, descr, price, calories, caffeine, image);
                list.add(bev);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }

    public Beverage getBevFromNameAndCafe(String name, String cafeteria) {

        List<Beverage> list = getAllBevs(cafeteria);

        for (Beverage bev : list) {
            if (bev.getName().equals(name)) {
                return bev;
            }
        }

        return null;
    }

    public void saveBevOrderList(List<Beverage> beverages, String cafeteria, String orderReq) {

        Connection conn = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO bevlist (bev, quantity, cafe, `order`) VALUES (?, ?, ?, ?)";

        int c;
        while (!beverages.isEmpty()) {

            c = 1;
            Beverage currBeverage = beverages.get(0);

            for (int i=1; i<beverages.size(); i++) {
                if(beverages.get(i).getName().equals(currBeverage.getName())) {
                    c++;
                    beverages.remove(i);
                }
            }


            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, currBeverage.getName());
                ps.setInt(2, c);
                ps.setString(3, cafeteria);
                ps.setString(4, orderReq);


                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

            beverages.remove(0);

        }


    }

    public List<Beverage> getBevOrderList(String orderReq) {

        List<Beverage> list = new ArrayList<>();

        String query = "SELECT * FROM bevlist WHERE `order` = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, orderReq);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("bev");
                String cafe = rs.getString("cafe");
                int quantity = rs.getInt("quantity");

                Beverage bev = getBevFromNameAndCafe(name, cafe);

                for(int i=0; i<quantity; i++){
                    list.add(bev);
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }



}
