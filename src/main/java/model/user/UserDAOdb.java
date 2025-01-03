package model.user;

import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOdb extends UserDAO{

    public void saveUser(User user){

        String query = "INSERT INTO user (username, password, role, cafeteria) VALUES (?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getCafeteria());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUser(){

        List<User> list = new ArrayList<>();

        String query = "SELECT username, password, role, cafeteria FROM user";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String cafeteria = rs.getString("cafeteria");


                User user = new User(username, password, role);
                user.setCafeteria(cafeteria);
                list.add(user);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;


    }


}
