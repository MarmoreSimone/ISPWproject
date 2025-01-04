package model.orderrequest;

import controller.SearchCafeteriaController;
import model.DAOfactory;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRequestDAOdb extends OrderRequestDAO{


    public void saveOrderRequest(OrderRequest order){

        String query = "INSERT INTO orderrequest (pickupcode, user, cafeteria, status) VALUES (?, ?, ?, ?)";
        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, order.getPickUpCode());
            ps.setString(2, order.getUser());
            ps.setString(3, order.getCafeteria().getName());
            ps.setString(4, order.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //salvo order
        DAOfactory.getDAOfactory().createOrderDAO().saveOrder(order.getOrder(), order.getPickUpCode(), order.getCafeteria().getName());

    }



    public List<OrderRequest> getAllOrderRequestsByUsername(String username){
        String query = "SELECT * FROM orderrequest WHERE user = ?";
        String user = username;

        return getAllOrderRequestsByQuery(query,user);
    }

    public List<OrderRequest> getAllOrderRequestsByCafeName(String cafeteria){
        String query = "SELECT * FROM orderrequest WHERE cafeteria = ?";
        String cafe = cafeteria;

        return getAllOrderRequestsByQuery(query,cafe);

    }

    private List<OrderRequest> getAllOrderRequestsByQuery(String myQuery, String key){

        List<OrderRequest> orderRequests = new ArrayList<OrderRequest>();

        String query = myQuery;

        Connection connection = DbConnection.getInstance().getConnection();

        SearchCafeteriaController search = new SearchCafeteriaController();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String pickupcode = rs.getString("pickupcode");
                String user = rs.getString("user");
                String cafe = rs.getString("cafeteria");
                String status = rs.getString("status");


                OrderRequest ord = new OrderRequest(user, search.getCafeteriaByName(cafe),status,pickupcode);
                ord.setOrder(DAOfactory.getDAOfactory().createOrderDAO().getOrderByOrderReq(pickupcode,cafe));

                orderRequests.add(ord);

            }


        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return orderRequests;
    }

    public void changeStatus(OrderRequest orderReq, String status){

        String query = "UPDATE orderrequest SET status = ? WHERE pickupcode = ?";

        Connection conn = DbConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, status);
            ps.setString(2, orderReq.getPickUpCode());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
