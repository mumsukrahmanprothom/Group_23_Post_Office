package com.postoffice.model;

public class AssistantPostMaster extends User {
    public static final String ROLE = "Assistant Post Master";

    public AssistantPostMaster(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    @Override
    public String getRole() { return ROLE; }

    @Override
    public String getDashboardFxml() { return "/com/postoffice/assistant_dashboard.fxml"; }
}
