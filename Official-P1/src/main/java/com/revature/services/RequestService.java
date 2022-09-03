package com.revature.services;

import com.revature.DAOs.RequestDAO;
import com.revature.entities.Request;

import java.util.List;

public class RequestService {
        private RequestDAO dao;

        public RequestService() {
            this.dao = new RequestDAO();
        }

        public void saveRequest(Request request){
            //we are calling the create method here
            //this method does not need to be called "create"
            dao.create(request);
        }

        public Request getRequest(int id){
            return dao.read(id);
        }
        public List<Request> getAllRequest(){
            return dao.readAll();
        }

        public List<Request> getRequestsForEmployee(Integer employeeId){
            List<Request> requestList = dao.readAll();

            for(Request request : requestList){ //for each request in the collection request list
                if(!request.getEmployeeId().equals(employeeId)){
                    requestList.remove(request);
                }
            }

            return requestList;
        }

        public void updateRequest(Request request){
            dao.update(request);
        }
        public void deleteRequest(int id){
            dao.delete(id);
        }
    }


