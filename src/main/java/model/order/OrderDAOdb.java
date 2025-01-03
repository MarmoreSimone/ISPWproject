package model.order;

import model.DAOfactory;
import model.beverage.Beverage;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOdb extends OrderDAO{


    public  List<Order> getAllOrders(){
        return null;
    }


    public Order getOrderByOrderReq(String orderReq, String cafeteria){

        Order order = null;

        String query = "SELECT * FROM myorder WHERE orderreq = ?";

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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //ora recupero le bevande
        order.setItems(DAOfactory.getDAOfactory().createBeverageDAO().getBevOrderList(orderReq));


        return order;



    }

    public void saveOrder(Order order, String orderReq, String cafeteria) {

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
            e.printStackTrace();
        }

        //salvo la lista di bevande
        List<Beverage> beverages = new ArrayList<>(order.getBevs());

        DAOfactory.getDAOfactory().createBeverageDAO().saveBevOrderList(beverages, cafeteria, orderReq);

        }
    }

