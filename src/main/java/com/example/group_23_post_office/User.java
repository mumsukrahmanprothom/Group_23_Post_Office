package com.example.group_23_post_office;

public abstract class User {

    private final String username;
    private String password;
    private final String fullName;
    private boolean active = true;

    protected User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean checkPassword(String attempt) {
        return password.equals(attempt);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return active ? "Active" : "Disabled";
    }

    public abstract String getRole();

    public abstract String getDashboardFxml();

    @Override
    public String toString() {
        return fullName + " (" + username + ", " + getRole() + ")";
    }
}