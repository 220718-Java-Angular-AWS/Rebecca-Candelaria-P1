package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;
import com.revature.services.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequestServlet extends HttpServlet {
    private RequestService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Request servlet initializing");
        this.service = new RequestService();
        this.mapper = new ObjectMapper();
        System.out.println("Request servlet initialized");
    }
    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("request-id");
        if (param == null) {
            List<Request> requestList = service.getAllRequest();
            String json = mapper.writeValueAsString(requestList);
            resp.getWriter().println(json);
        } else {

            Integer requestId = Integer.parseInt(req.getParameter("request-id"));
            Request request = service.getRequest(requestId);
            String json = mapper.writeValueAsString(request);
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

        Request request = mapper.readValue(json, Request.class);

        service.saveRequest(request);
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("request-id");
        Integer requestId = Integer.parseInt(param);
        service.deleteRequest(requestId);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
        }
}

