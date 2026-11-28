package com.example.group_23_post_office;

public class DataEntryOperator {
    private int operatorID;
    private String username, password;
    public DataEntryOperator(int operatorID, String username, String password) {
        this.operatorID = operatorID; this.username = username; this.password = password;
    }
    public int getOperatorID() { return operatorID; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public boolean login(String name, String pass) { return username.equals(name) && password.equals(pass); }
    public String toString() { return username + " (Data Entry Operator)"; }
}
