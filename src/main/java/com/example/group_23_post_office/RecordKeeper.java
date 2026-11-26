package com.example.group_23_post_office;

public class RecordKeeper {
    private int keeperID;
    private String username, password;
    public RecordKeeper(int keeperID, String username, String password) {
        this.keeperID = keeperID; this.username = username; this.password = password;
    }
    public int getKeeperID() { return keeperID; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public boolean login(String name, String pass) { return username.equals(name) && password.equals(pass); }
    public String toString() { return username + " (Record Keeper)"; }
}
