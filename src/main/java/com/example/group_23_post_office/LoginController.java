package com.example.group_23_post_office;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private UserService userService = new UserService();

    @FXML
    private void handleLogin(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userService.login(username, password);

        if (user != null) {

            Session.setCurrentUser(user);

            try {

                String dashboardFxml = user.getDashboardFxml();

                URL location = getClass().getResource(dashboardFxml);

                if (location == null) {
                    messageLabel.setText(
                            "Dashboard FXML not found: " + dashboardFxml);
                    System.out.println("FXML path: " + dashboardFxml);
                    return;
                }

                FXMLLoader loader = new FXMLLoader(location);

                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException | RuntimeException e) {

                messageLabel.setText("Unable to open dashboard.");

                e.printStackTrace();
            }

        } else {

            messageLabel.setText(
                    "Invalid username or password.");
        }
    }
}
