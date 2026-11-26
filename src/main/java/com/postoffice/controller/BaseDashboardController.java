package com.postoffice.controller;

import com.postoffice.model.User;
import com.postoffice.service.ActivityLogger;
import com.postoffice.service.Session;
import com.postoffice.util.SceneNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BaseDashboardController {
    @FXML protected Label welcomeLabel;

    protected void showWelcome() {
        User u = Session.getCurrentUser();
        welcomeLabel.setText("Welcome, " + u.getFullName() + "  |  Role: " + u.getRole());
    }

    @FXML
    protected void handleLogout() {
        User u = Session.getCurrentUser();
        if (u != null) {
            ActivityLogger.getInstance().log(u, "Logged out");
        }
        Session.clear();
        try {
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            SceneNavigator.switchTo(stage, "/com/postoffice/login.fxml",
                    "Post Office Simulation - Login", 420, 380);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
