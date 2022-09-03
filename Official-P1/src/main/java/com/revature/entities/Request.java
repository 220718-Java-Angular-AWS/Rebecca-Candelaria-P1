package com.revature.entities;

import java.util.Objects;

public class Request {
    private Integer requestID;
    private String requestDate;
    private String purchaseDate;
    private String city;
    private String state;
    private Integer zipCode;
    private Integer amount;
    private String requestReason;
    private Integer employeeId;
    private Boolean completed;

    public Request() {
    }

    public Request(Integer requestID, String requestDate, String purchaseDate, String city, String state, Integer zipCode, Integer amount, String requestReason, Integer employeeId, Boolean completed) {
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.purchaseDate = purchaseDate;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.amount = amount;
        this.requestReason = requestReason;
        this.employeeId = employeeId;
        this.completed = completed;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestID, request.requestID) && Objects.equals(requestDate, request.requestDate) && Objects.equals(purchaseDate, request.purchaseDate) && Objects.equals(city, request.city) && Objects.equals(state, request.state) && Objects.equals(zipCode, request.zipCode) && Objects.equals(amount, request.amount) && Objects.equals(requestReason, request.requestReason) && Objects.equals(employeeId, request.employeeId) && Objects.equals(completed, request.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestID, requestDate, purchaseDate, city, state, zipCode, amount, requestReason, employeeId, completed);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestID=" + requestID +
                ", requestDate='" + requestDate + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", amount=" + amount +
                ", requestReason='" + requestReason + '\'' +
                ", employeeId=" + employeeId +
                ", completed=" + completed +
                '}';
    }
}
