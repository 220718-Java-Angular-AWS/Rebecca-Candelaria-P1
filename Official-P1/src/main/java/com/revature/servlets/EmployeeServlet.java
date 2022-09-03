package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.Employee;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    EmployeeService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Employee Servlet Initializing");
       this.service = new EmployeeService();
       this.mapper = new ObjectMapper();
        System.out.println("Employee Servlet Initialized");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("employee-id");
        if (param == null) {

            List<Employee> employeeList = service.getAllUsers();
            String json = mapper.writeValueAsString(employeeList);
            resp.getWriter().println(json);

        } else {

            Integer employeeId = Integer.parseInt(req.getParameter("employee-id"));
            Employee employee = service.getEmployee(employeeId);
            String json = mapper.writeValueAsString(employee);
            resp.getWriter().println(json);

        }
        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       StringBuilder sb = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()){
           sb.append(buffer.readLine());
        }
        String json = sb.toString();
        System.out.println(json);
        Employee employee = mapper.readValue(json, Employee.class);
        service.saveEmployee(employee);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("employee-id");


        StringBuilder sb = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()){
            sb.append(buffer.readLine());
        }
        String json = sb.toString();

        Employee employee = mapper.readValue(json, Employee.class);
        Integer employeeId = Integer.parseInt(param);
        System.out.println(employee.toString());
        employee.setEmployeeId(employeeId);
        System.out.println(employee.toString());
        service.updateEmployee(employee);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("employee-id");
        Integer employeeId = Integer.parseInt(param);
        service.deleteEmployee(employeeId);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }
}
