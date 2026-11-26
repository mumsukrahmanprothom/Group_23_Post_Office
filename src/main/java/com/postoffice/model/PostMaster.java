package com.postoffice.model;

public class PostMaster extends User {
    public static final String ROLE = "Post Master";

    public PostMaster(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    @Override
    public String getRole() { return ROLE; }

    @Override
    public String getDashboardFxml() { return "/com/postoffice/postmaster_dashboard.fxml"; }
}
