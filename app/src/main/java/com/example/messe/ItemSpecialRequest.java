package com.example.messe;

public class ItemSpecialRequest {
    private String firstName;
    private String lastName;
    private String reqId;
    private String reqMessage;
    private String date;
    private String status;
    public ItemSpecialRequest(String firstName, String lastName, String reqId, String reqMessage, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reqId = reqId;
        this.reqMessage = reqMessage;
        this.status = status;
    }
    // getters
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getReqId() {return reqId;}
    public String getReqMessage() {return reqMessage;}
    public String getStatus() {return status;}

    // setters
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setReqId(String reqId) {this.reqId = reqId;}
    public void setReqMessage(String reqMessage) {this.reqMessage = reqMessage;}
    public void setStatus(String status) {this.status = status;}
}
