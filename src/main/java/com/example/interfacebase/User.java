package com.example.interfacebase;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int group;
    private int type;

    public User(int id, String firstName, String lastName, String userName, String password, int group, int type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.group = group;
        this.type = type;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
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

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
