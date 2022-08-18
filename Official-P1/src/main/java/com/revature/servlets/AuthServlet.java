package com.revature.servlets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.services.EmployeeService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper;
    EmployeeService service;

    @Override
    public void init() throws ServletException {
        System.out.println("AuthServlet Initializing");
        this.mapper = new ObjectMapper();
        this.service = new EmployeeService();
        System.out.println("AuthServlet Initialized");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Authenticate

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee employee = service.login(username, password);
        String json = mapper.writeValueAsString(employee);
        resp.getWriter().println(json);
        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");


    }
}