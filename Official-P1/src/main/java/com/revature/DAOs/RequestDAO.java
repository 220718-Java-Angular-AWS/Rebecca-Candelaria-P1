package com.revature.DAOs;

import com.revature.entities.Request;
import com.revature.services.DataSourceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RequestDAO implements DataSourceCRUD<Request> {

    Connection connection;

    public RequestDAO() {
            connection = DataSourceService.getConnection();
        }

    @Override
    public void create(Request request) {
        try {
            String sql = "INSERT INTO requests (request_date, purchase_date, city, state, zip_code, amount, request_reason, employee_id, completed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            //Date formatDate =
            pstmt.setString(1, request.getRequestDate());
            pstmt.setString(2, request.getPurchaseDate());
            pstmt.setString(3, request.getCity());
            pstmt.setString(4, request.getState());
            pstmt.setInt(5, request.getZipCode());
            pstmt.setInt(6, request.getAmount());
            pstmt.setString(7, request.getRequestReason());
            pstmt.setInt(8, request.getEmployeeId());
            pstmt.setBoolean(9, request.getCompleted());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Request read(int id) {
        Request request = new Request();
        try {
            String sql = "SELECT * FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet results = pstmt.executeQuery();

            if (results.next()) {

                request.setRequestID(results.getInt("request_id"));
                request.setRequestDate(results.getString("request_date"));
                request.setPurchaseDate(results.getString("purchase_Date"));
                request.setCity(results.getString("city"));
                request.setState(results.getString("state"));
                request.setZipCode(results.getInt("zip_code"));
                request.setAmount(results.getInt("amount"));
                request.setRequestReason(results.getString("request_reason"));
                request.setEmployeeId(results.getInt("employee_id"));
                request.setCompleted(results.getBoolean("completed"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public List<Request> readAll() {
        List<Request> requestList = new LinkedList<>();

        try {
            String sql = "SELECT * FROM requests";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                Request request = new Request();
                request.setRequestID(results.getInt("request_id"));
                request.setRequestDate(results.getString("request_date"));
                request.setPurchaseDate(results.getString("purchase_Date"));
                request.setCity(results.getString("city"));
                request.setState(results.getString("state"));
                request.setZipCode(results.getInt("zip_code"));
                request.setAmount(results.getInt("amount"));
                request.setRequestReason(results.getString("request_reason"));
                request.setEmployeeId(results.getInt("employee_id"));
                request.setCompleted(results.getBoolean("completed"));
                requestList.add(request);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }


    @Override
    public void update(Request request) {
       try {
           String sql = "UPDATE requests SET request_date = ?, purchase_date = ?, city = ?, state = ?, zip_code = ?, amount = ?, request_reason = ?, employee_id = ?, completed = ? WHERE request_id = ?";
           PreparedStatement pstmt = connection.prepareStatement(sql);
           pstmt.setString(1, request.getRequestDate());
           pstmt.setString(2, request.getPurchaseDate());
           pstmt.setString(3, request.getCity());
           pstmt.setString(4, request.getState());
           pstmt.setInt(5, request.getZipCode());
           pstmt.setInt(6, request.getAmount());
           pstmt.setString(7, request.getRequestReason());
           pstmt.setInt(8, request.getEmployeeId());
           pstmt.setBoolean(9, request.getCompleted());
           pstmt.setInt(10, request.getRequestID());

           pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        try {
            String sql = "DELETE FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
