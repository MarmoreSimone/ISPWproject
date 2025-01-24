package model.user;

import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOdb extends UserDAO{

    private String defaultDbProblem = "internal db error1";

    private void saveUser(User user, String cafeteria) throws SystemErrorException{

        String query = "INSERT INTO user (username, password, role, cafeteria) VALUES (?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, cafeteria);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SystemErrorException("username already taken");
        }

    }

    public void saveClient(Client client) throws SystemErrorException{
        saveUser(client,null);
    }

    public void saveBarista(Barista bar) throws SystemErrorException{
        if(bar.getCafeteria() == null) saveUser(bar,null);
        else saveUser(bar,bar.getCafeteria().getName());
    }

    private List<Barista> getAllBaristas() throws SystemErrorException{

        List<Barista> baristas = new ArrayList<Barista>();

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "SELECT username, password, role, cafeteria FROM user WHERE role = 'barista'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String cafeteria = rs.getString("cafeteria");


                Barista user = DAOfactory.getDAOfactory().createUserDAO().createNewUserBarista(username,password,role);

                //caso in cui il barista non ha ancora impostato una caffetteria
                if(cafeteria != null) {
                    user.setCafeteria(DAOfactory.getDAOfactory().createCafeteriaDAO().getCafeteriaByName(cafeteria));
                }

                baristas.add(user);

            }

        } catch (SQLException | NoCafeteriasFoundException e){
            throw new SystemErrorException(defaultDbProblem);
        }

        return baristas;

    }

    private List<Client> getAllClients() throws SystemErrorException{

        List<Client> clients = new ArrayList<Client>();

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "SELECT username, password, role FROM user WHERE role = 'client'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                Client user = DAOfactory.getDAOfactory().createUserDAO().createNewUserClient(username,password,role);
                user.setOrderRequestList(DAOfactory.getDAOfactory().createOrderRequestDAO().getAllOrderRequestsByUsername(username));
                clients.add(user);

            }

        } catch (SQLException | SystemErrorException e) {
            throw new SystemErrorException(defaultDbProblem);
        }

        return clients;

    }

    public List<User> getAllUserCredentials() throws SystemErrorException{

        List<User> users = new ArrayList<>();

        users.addAll(getAllBaristas());
        users.addAll(getAllClients());

        return users;
    }



    public void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria) throws SystemErrorException{

        String query = "UPDATE user SET cafeteria = ? WHERE username = ?";

        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, cafeteria.getName());
            ps.setString(2, barista.getUsername());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SystemErrorException(defaultDbProblem);
        }

    }

}
