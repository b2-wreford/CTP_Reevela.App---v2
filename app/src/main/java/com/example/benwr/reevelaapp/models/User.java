package com.example.benwr.reevelaapp.models;


import java.util.HashMap;
import java.util.Map;

/*
needs to match exactly to names on firebase
 */
public class User {

    private String user_id;
    private long phone_number;
    private String email;
    private String username;
    private String imageURL;
    private String status;
    public Map<String, Boolean> stars = new HashMap<>();


    public User(String user_id, long phone_number, String email, String username, String imageURL, String status) {   //String status
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
    }

    public User() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", phone_number=" + phone_number +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}