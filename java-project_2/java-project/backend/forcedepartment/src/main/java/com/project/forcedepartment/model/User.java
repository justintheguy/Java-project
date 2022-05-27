package com.project.forcedepartment.model;

import java.util.Calendar;
import java.util.Date;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private int age;
    private Date registrationDate;
    private Date birthOfDate;
    private boolean isAdmin;
    private String userType;
    private String password;
    private String email;
    private String image = "profile-icon-empty.png";
    private String imageName;
    private static final boolean IS_ADMIN = false;
    //private String profileImage;

    public User() {
    }

    public User(String firstName, String lastName, Date birthOfDate, String userType, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthOfDate = birthOfDate;
        this.userType = userType;
        this.email = email;
    }

    public User(int id, String firstName, String lastName, Date registrationDate, Date birthOfDate, String userType, String email) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.birthOfDate = birthOfDate;
        this.userType = userType;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getImageName() {
        return firstName + " " + lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {

        Calendar actualDate = Calendar.getInstance();
        actualDate.setTime(getRegistrationDate());

        Calendar birthOfDate = Calendar.getInstance();
        birthOfDate.setTime(getBirthOfDate());

        return actualDate.get(Calendar.YEAR) - birthOfDate.get(Calendar.YEAR);
    }


    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //do regex to check the email
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", registrationDate='" + registrationDate + '\'' +
                ", birthOfDate='" + birthOfDate + '\'' +
                ", isAdmin=" + isAdmin +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                "}\n";
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }
}
