package com.rpl.homebase.model;

/**
 *
 * @author SuryaNKT
 */
public class User {
    private int userID;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String level;
    private String bidang;

    public User() {
    }

    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(int userID, String username, String password, String name, String gender, String level) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.level = level;
    }

    public User(int userID, String username, String password, String name, String gender, String level, String bidang) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.bidang = bidang;
    }

    public String getBidang() {
        return bidang;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}