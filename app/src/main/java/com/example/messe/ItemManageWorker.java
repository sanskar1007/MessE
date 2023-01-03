package com.example.messe;

public class ItemManageWorker {
    private String firstName;
    private String lastName;
    private String id;
    private String overallRating;
    private String countRating;
    private String attendance;
    private String lastAttendanceDate;
    private String status;

    public ItemManageWorker(String firstName, String lastName, String id, String overallRating, String countRating, String attendance, String lastAttendanceDate,String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.overallRating = overallRating;
        this.countRating = countRating;
        this.status = status;
        this.attendance = attendance;
        this.lastAttendanceDate = lastAttendanceDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public String getCountRating() {
        return countRating;
    }

    public String getAttendance() {
        return attendance;
    }

    public String getStatus() {
        return status;
    }

    public String getLastAttendanceDate() {return lastAttendanceDate;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }

    public void setCountRating(String countRating) {
        this.countRating = countRating;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastAttendanceDate(String lastAttendanceDate) {this.lastAttendanceDate = lastAttendanceDate;}
}
