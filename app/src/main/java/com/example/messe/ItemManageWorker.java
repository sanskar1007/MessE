package com.example.messe;

public class ItemManageWorker {
    private String firstName;
    private String lastName;
    private String id;
    private String overallRating;
    private String countRating;
    private String date;
    private String status;

    public ItemManageWorker(String firstName, String lastName, String id, String overallRating, String countRating, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.overallRating = overallRating;
        this.countRating = countRating;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

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

    public void setStatus(String status) {
        this.status = status;
    }
}
