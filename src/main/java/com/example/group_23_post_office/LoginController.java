package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    @FXML private void login() {
        DataEntryOperator operator = new DataEntryOperator(5, "user5", "1234");
        RecordKeeper keeper = new RecordKeeper(6, "user6", "1234");
        try {
            RecordStore store = RecordStore.load();
            boolean isKeeper = keeper.login(usernameField.getText(), passwordField.getText());
            if (!isKeeper && !operator.login(usernameField.getText(), passwordField.getText())) {
                messageLabel.setText("Incorrect username or password."); return;
            }
            if (!isKeeper && !store.getPermission().canRead()) {
                messageLabel.setText("User 5 has no active record access."); return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("workspace.fxml"));
            Scene scene = new Scene(loader.load(), 1120, 730);
            WorkspaceController controller = loader.getController();
            controller.setup(store, isKeeper);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene); stage.setMinWidth(900); stage.setMinHeight(650);
        } catch (Exception e) { messageLabel.setText("Cannot open project: " + e.getMessage()); }
    }
}
