package com.postoffice.controller;

import com.postoffice.model.AssistantPostMaster;
import com.postoffice.model.PostMaster;
import com.postoffice.model.User;
import com.postoffice.service.ActivityLogger;
import com.postoffice.service.Session;
import com.postoffice.service.UserService;
import com.postoffice.util.SceneNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleCombo;
    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
        roleCombo.getItems().addAll(PostMaster.ROLE, AssistantPostMaster.ROLE);
        roleCombo.getSelectionModel().selectFirst();
        errorLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String role = roleCombo.getValue();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter username and password.");
            return;
        }

        User user = UserService.getInstance().authenticate(username, password, role);
        if (user == null) {
            ActivityLogger.getInstance().logUnknown(username, "FAILED login attempt as " + role);
            errorLabel.setText("Invalid username, password or role.");
            passwordField.clear();
            return;
        }

        Session.setCurrentUser(user);
        ActivityLogger.getInstance().log(user, "Logged in");

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            SceneNavigator.switchTo(stage, user.getDashboardFxml(),
                    "Post Office - " + user.getRole(), 1180, 720);
        } catch (IOException e) {
            errorLabel.setText("Could not open dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
