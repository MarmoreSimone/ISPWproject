package model.menuitem;

import model.DAOfactory;
import model.cafeteria.Cafeteria;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAOdb extends MenuItemDAO {

    @Override
    public void saveItem(MenuItem bev, Cafeteria cafeteria) {

        String query = "INSERT INTO menuitem (name, description, price, calories, caffeine, image, cafeteria,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bev.getName());
            ps.setString(2, bev.getDescription());
            ps.setDouble(3, bev.getPrice());
            ps.setDouble(4, bev.getCalories());
            ps.setDouble(5, bev.getCaffeine());
            ps.setString(6, bev.getImage());
            ps.setString(7, cafeteria.getName());
            ps.setString(8, bev.getType());

            // Esegui la query
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<MenuItem> getAllItems(Cafeteria cafeteria) {
        List<MenuItem> list = new ArrayList<>();
        String query = "SELECT name, description, price, calories, caffeine, image, type FROM menuitem WHERE cafeteria = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, cafeteria.getName());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String descr = rs.getString("description");
                Double price = rs.getDouble("price");
                Double calories = rs.getDouble("calories");
                Double caffeine = rs.getDouble("caffeine");
                String image = rs.getString("image");
                String type = rs.getString("type");
                MenuItem bev = new MenuItem(name, descr, price, calories, caffeine, image, type);
                list.add(bev);

            }


        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return list;

    }

    private MenuItem getItemFromNameAndCafe(String name, String cafeteria) {

        Cafeteria cafe = DAOfactory.getDAOfactory().createCafeteriaDAO().createCafeteria(cafeteria,null,null,null,null,null);
        List<MenuItem> list = getAllItems(cafe);

        for (MenuItem bev : list) {
            if (bev.getName().equals(name)) {
                return bev;
            }
        }

        return null;
    }

    public void saveItemOrderList(List<MenuItem> items, String cafeteria, String orderReq) {

        List<MenuItem> products = new ArrayList<>();

        for(int i=0;i<items.size();i++) {

            if(items.get(i).getName().contains("\n")) saveCustomItem(items.get(i), orderReq);
            else products.add(items.get(i));

        }

        Connection conn = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO itemlist (item, quantity, cafe, `order`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(3, cafeteria);
            ps.setString(4, orderReq);
            int c;
            while (!products.isEmpty()) {

                c = 1;
                MenuItem currBeverage = products.get(0);

                for (int i = products.size() - 1; i > 0; i--) {
                    if(products.get(i).getName().equals(currBeverage.getName())) {
                        c++;
                        products.remove(i);
                    }
                }

                ps.setString(1, currBeverage.getName());
                ps.setInt(2, c);

                ps.addBatch();

                products.remove(0);

        }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void saveCustomItem(MenuItem bev, String orderReq) {

        String query = "INSERT INTO customitemlist (name, description, price, calories, caffeine, image, type, orderReq) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bev.getName());
            ps.setString(2, bev.getDescription());
            ps.setDouble(3, bev.getPrice());
            ps.setDouble(4, bev.getCalories());
            ps.setDouble(5, bev.getCaffeine());
            ps.setString(6, bev.getImage());
            ps.setString(7, bev.getType());
            ps.setString(8, orderReq);

            // Esegui la query
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<MenuItem> getItemOrderList(String orderReq) {

        List<MenuItem> list = new ArrayList<>();

        //prima recupero gli item non modificati
        String query = "SELECT item,quantity,cafe FROM itemlist WHERE `order` = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, orderReq);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("item");
                String cafe = rs.getString("cafe");
                int quantity = rs.getInt("quantity");

                MenuItem bev = getItemFromNameAndCafe(name, cafe);

                for(int i=0; i<quantity; i++){
                    list.add(bev);
                }

            }


        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }


        query = "SELECT name, description, price, calories, caffeine, image, type FROM customitemlist WHERE orderReq = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, orderReq);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String descr = rs.getString("description");
                Double price = rs.getDouble("price");
                Double calories = rs.getDouble("calories");
                Double caffeine = rs.getDouble("caffeine");
                String image = rs.getString("image");
                String type = rs.getString("type");
                MenuItem bev = new MenuItem(name, descr, price, calories, caffeine, image, type);
                list.add(bev);

            }


        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return list;
    }



}
