package com.revature.entities;

import java.util.Objects;

public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String deptName;
    private String userName;
    private String password;

    public Employee() {
    }

    public Employee(Integer employeeId, String firstName, String lastName, String deptName, String userName, String password) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptName = deptName;
        this.userName = userName;
        this.password = password;
    }

    public Employee(String firstName, String lastName, String deptName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptName = deptName;
        this.userName = userName;
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(deptName, employee.deptName) && Objects.equals(userName, employee.userName) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, deptName, userName, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


