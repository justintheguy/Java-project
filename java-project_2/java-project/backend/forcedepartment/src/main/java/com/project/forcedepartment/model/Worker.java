package com.project.forcedepartment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Worker extends User {

    private String description;
    private String phoneNumber;
    private double rate;
    //change into hashMap
    private List<String> profession;

    public Worker(String firstName, String lastName, Date birthOfDate, String userType,
                  String email, String description, String phoneNumber, List<String> profession, double rate) {
        super(firstName, lastName, birthOfDate, userType, email);
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.profession = new ArrayList<>();
        this.profession = profession;
        this.rate = rate;
    }

    public Worker(int userId, String firstName, String lastName, Date registrationDate, Date birthOfDate, String userType, String email,
                  String description, String phoneNumber, List<String> profession, double rate) {
        super(userId, firstName, lastName, registrationDate, birthOfDate, userType, email);
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.rate = rate;
        this.profession = new ArrayList<>();
        this.profession = profession;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getProfession() {
        return profession;
    }

    public void setProfession(List<String> profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rate=" + rate +
                ", profession=" + profession +
                '}';
    }
}
