package model.order;

import exception.SystemErrorException;
import model.DAOfactory;
import model.menuitem.MenuItem;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOdb extends OrderDAO{

    private static final String DEFAULT_DB_PROBLEM = "internal db error";


    public Order getOrderByOrderReq(String orderReq, String cafeteria) throws SystemErrorException{

        Order order = null;

        String query = "SELECT `date` , totprice , `time`, note, paymeth FROM myorder WHERE orderreq = ?";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, orderReq);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String date = rs.getString("date");
                Double totprice = rs.getDouble("totprice");
                String time = rs.getString("time");
                String note = rs.getString("note");
                String paymethod = rs.getString("paymeth");
                order = new Order(totprice, paymethod, note, date, time);

            }

        //ora recupero le bevande
        order.setItems(DAOfactory.getDAOfactory().createMenuItemDAO().getItemOrderList(orderReq));


        return order;

        } catch (SQLException | SystemErrorException e) {
            throw new SystemErrorException(DEFAULT_DB_PROBLEM);
        }


    }

    public void saveOrder(Order order, String orderReq, String cafeteria) throws SystemErrorException{

        String query = "INSERT INTO myorder (orderreq, totprice, `date`, `time`, note, paymeth) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, orderReq);
            ps.setDouble(2, order.getTotPrice());
            ps.setString(3, order.getDate());
            ps.setString(4, order.getTime());
            ps.setString(5, order.getNote());
            ps.setString(6, order.getPayMethod());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new SystemErrorException(DEFAULT_DB_PROBLEM);
        }

        //salvo la lista di bevande
        List<MenuItem> beverages = new ArrayList<>(order.getBevs());

        DAOfactory.getDAOfactory().createMenuItemDAO().saveItemOrderList(beverages, cafeteria, orderReq);

        }
    }

