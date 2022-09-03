package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.Request;
import com.revature.services.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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

        Integer requestId = Integer.parseInt(req.getParameter("request-id"));
        List<Request> requestList = new ArrayList<>();
        Request request = service.getRequest(requestId);
        requestList.add(request);
        String json = mapper.writeValueAsString(requestList);
        resp.getWriter().println(json);

        resp.setStatus(200);
        resp.setContentType("Application/Json");
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("request-id");


        StringBuilder sb = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()){
            sb.append(buffer.readLine());
        }
        String json = sb.toString();

        Request request = mapper.readValue(json, Request.class);
        Integer requestId = Integer.parseInt(param);
        System.out.println(request.toString());
        request.setRequestID(requestId);
        System.out.println(request.toString());
        service.updateRequest(request);
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

