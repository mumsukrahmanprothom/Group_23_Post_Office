package com.example.group_23_post_office;

public class CSO extends User {

    public static final String ROLE = "CSO";

    public CSO(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public String getDashboardFxml() {
        return "/com/example/group_23_post_office/cso-dashboard.fxml";
    }
}