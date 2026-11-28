package com.example.group_23_post_office;

public class MRO extends User {

    public static final String ROLE = "MRO";

    public MRO(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public String getDashboardFxml() {
        return "/com/example/group_23_post_office/mro-dashboard.fxml";
    }
}