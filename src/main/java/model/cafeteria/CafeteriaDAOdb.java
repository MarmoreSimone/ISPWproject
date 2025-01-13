package model.cafeteria;

import exception.NoCafeteriasFoundException;
import model.DAOfactory;
import utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAOdb extends CafeteriaDAO{

    public void saveCafeteria(Cafeteria cafe) {

        String query = "INSERT INTO cafeteria (name, city, address, number, description, photo) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, cafe.getName());
            ps.setString(2, cafe.getCity());
            ps.setString(3, cafe.getAddress());
            ps.setString(4, cafe.getNumber());
            ps.setString(5, cafe.getDescription());
            ps.setString(6, cafe.getPhoto());

            // Esegui la query
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Cafeteria getCafeteriaByName(String cafeName) throws NoCafeteriasFoundException {


        Cafeteria cafe;
        String query = "SELECT name,city,address,number,description,photo FROM cafeteria WHERE name = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, cafeName);
            ResultSet rs = ps.executeQuery();
            rs.next();

                String name = rs.getString("name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String number = rs.getString("number");
                String desc = rs.getString("description");
                String image = rs.getString("photo");
                cafe = new Cafeteria(name, city, address, number, desc, image);
                cafe.setAllBeverage(DAOfactory.getDAOfactory().createBeverageDAO().getAllBevs(name));


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoCafeteriasFoundException(": no cafeteria with this name found in the system",e);
        }

        return cafe;
    }

    public List <Cafeteria> getAllCafeterias() throws NoCafeteriasFoundException {

        List<Cafeteria> list = new ArrayList<>();
        String query = "SELECT name,city,address,number,description,photo FROM cafeteria";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String number = rs.getString("number");
                String desc = rs.getString("description");
                String image = rs.getString("photo");
                Cafeteria caf = new Cafeteria(name, city, address, number, desc, image);
                caf.setAllBeverage(DAOfactory.getDAOfactory().createBeverageDAO().getAllBevs(name));

                list.add(caf);

            }


        } catch (SQLException e) {
            throw new NoCafeteriasFoundException(": no cafeteria with this name found in the system");
        }

        return list;

    }


}
