package com.revature.services;

import com.revature.DAOs.EmployeeDAO;
import com.revature.pojos.Employee;
import com.revature.pojos.Request;

import java.util.List;


public class EmployeeService {
    private EmployeeDAO dao;

    public EmployeeService() {
        this.dao = new EmployeeDAO();
    }

    public void saveEmployee(Employee employee) {
        //we are calling the create method here
        //this method does not need to be called "create"
        dao.create(employee);
    }

    public Employee getEmployee(int id){

        return dao.read(id);
    }
    public List<Employee> getAllUsers(){

        return dao.readAll();
    }

    public List<Employee> getEmployeeInfo(Integer employeeId){
        List<Employee> employeeList = dao.readAll();

        for(Employee employee : employeeList){
            if(!employee.getEmployeeId().equals(employeeId)){
                employeeList.remove(employee);
            }
        }

        return employeeList;
    }

    public void updateEmployee(Employee employee){
        dao.update(employee);
    }
    public void deleteEmployee(int id){
        dao.delete(id);
    }

    public Employee login(String username, String password){
        return dao.getLoginInfo(username, password);
    }
}
