package com.revature.DAOs;

import com.revature.pojos.Employee;
import com.revature.services.DataSourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAO implements DataSourceCRUD<Employee>{

    Connection connection;

    public EmployeeDAO()
    {
       connection = DataSourceService.getConnection();
    }

    @Override
    public void create(Employee employee) {
        String sql ="INSERT INTO employees (first_name, last_name, dept_name, user_name, pass_word) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getDeptName());
            pstmt.setString(4, employee.getUserName());
            pstmt.setString(5, employee.getPassword());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()){
                Integer key = keys.getInt("user_id");
                System.out.println("key: " + key);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee read(int id) {
        Employee employee = new Employee();

        try {
            String sql = "SELECT * FROM employees WHERE employee_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

           ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                employee.setEmployeeId(results.getInt("employee_id"));
                employee.setFirstName(results.getString("first_name"));
                employee.setLastName(results.getString("last_name"));
                employee.setDeptName(results.getString("dept_name"));
                employee.setUserName(results.getString("user_name"));
                employee.setPassword(results.getString("pass_word"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(results.getInt("employee_id"));
                employee.setFirstName(results.getString("first_name"));
                employee.setLastName(results.getString("last_name"));
                employee.setDeptName(results.getString("dept_name"));
                employee.setUserName(results.getString("user_name"));
                employee.setPassword(results.getString("pass_word"));
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
    @Override
    public void update(Employee employee) {

        try {
            String sql ="UPDATE employees SET first_name = ?, last_name = ?, dept_name = ?, user_name = ?, pass_word = ?, WHERE employee_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2,employee.getLastName());
            pstmt.setString(3, employee.getDeptName());
            pstmt.setString(4, employee.getUserName());
            pstmt.setString(5, employee.getPassword());
            pstmt.setInt(6, employee.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {

        try {
            String sql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public Employee getLoginInfo(String username, String password) {
            Employee employee = new Employee();

            try {
                String sql = "SELECT * FROM employees WHERE user_name = ? AND pass_word = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,username);
                pstmt.setString(2,password);

                ResultSet results = pstmt.executeQuery();

                if (results.next()) {
                    employee.setEmployeeId(results.getInt("employee_id"));
                    employee.setFirstName(results.getString("first_name"));
                    employee.setLastName(results.getString("last_name"));
                    employee.setDeptName(results.getString("dept_name"));
                    employee.setUserName(results.getString("user_name"));
                    employee.setPassword(results.getString("pass_word"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employee;
        }

}
