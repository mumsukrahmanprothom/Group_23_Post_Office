package com.postoffice.controller;

import com.postoffice.model.AssistantPostMaster;
import com.postoffice.model.PostMaster;
import com.postoffice.model.StaffMember;
import com.postoffice.model.User;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.service.UserService;
import com.postoffice.util.Ui;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AccountsController {
    @FXML private TableView<User> userTable;
    @FXML private TextField newUsernameField;
    @FXML private TextField newFullNameField;
    @FXML private PasswordField newPasswordField;
    @FXML private Label accountStatusLabel;

    @FXML private TableView<StaffMember> staffTable;
    @FXML private TextField staffNameField;
    @FXML private TextField staffPositionField;
    @FXML private Label staffStatusLabel;

    private final DataStore store = DataStore.getInstance();
    private final UserService users = UserService.getInstance();

    @FXML
    public void initialize() {
        refreshUsers();
        staffTable.setItems(store.getStaff());
    }

    public void refresh() {
        refreshUsers();
        staffTable.refresh();
    }

    private void refreshUsers() {
        userTable.setItems(FXCollections.observableArrayList(users.getAllUsers()));
        userTable.refresh();
    }

    @FXML
    private void handleCreateAssistant() {
        String username = newUsernameField.getText().trim();
        String fullName = newFullNameField.getText().trim();
        String password = newPasswordField.getText();
        if (username.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
            accountStatusLabel.setText("Fill in all three fields.");
            return;
        }
        if (!users.register(new AssistantPostMaster(username, password, fullName))) {
            accountStatusLabel.setText("Username already exists.");
            return;
        }
        if (store.findStaff(fullName) == null) {
            store.getStaff().add(new StaffMember(fullName, AssistantPostMaster.ROLE));
        }
        Session.log("Created assistant account '" + username + "'");
        accountStatusLabel.setText("Account created for " + username);
        newUsernameField.clear();
        newFullNameField.clear();
        newPasswordField.clear();
        refreshUsers();
        refresh();
    }

    @FXML
    private void handleResetPassword() {
        User u = userTable.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(u)) return;
        String pw = Ui.ask("Reset password", "New password for " + u.getUsername() + ":", "");
        if (pw == null) return;
        u.setPassword(pw);
        Session.log("Reset password of '" + u.getUsername() + "'");
        accountStatusLabel.setText("Password changed for " + u.getUsername());
    }

    @FXML
    private void handleToggleActive() {
        User u = userTable.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(u)) return;
        if (u instanceof PostMaster) {
            Ui.warn("The Post Master account cannot be disabled.");
            return;
        }
        u.setActive(!u.isActive());
        String what = "Disabled";
        if (u.isActive()) what = "Enabled";
        Session.log(what + " account '" + u.getUsername() + "'");
        refreshUsers();
    }

    @FXML
    private void handleDeleteAccount() {
        User u = userTable.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(u)) return;
        if (u instanceof PostMaster) {
            Ui.warn("The Post Master account cannot be deleted.");
            return;
        }
        if (!Ui.confirm("Delete account '" + u.getUsername() + "'?")) return;
        users.remove(u.getUsername());
        Session.log("Deleted account '" + u.getUsername() + "'");
        refreshUsers();
    }

    @FXML
    private void handleAddStaff() {
        String name = staffNameField.getText().trim();
        String position = staffPositionField.getText().trim();
        if (name.isEmpty() || position.isEmpty()) {
            staffStatusLabel.setText("Enter a name and a position.");
            return;
        }
        if (store.findStaff(name) != null) {
            staffStatusLabel.setText("That staff member already exists.");
            return;
        }
        store.getStaff().add(new StaffMember(name, position));
        Session.log("Added staff member " + name + " (" + position + ")");
        staffNameField.clear();
        staffPositionField.clear();
        staffStatusLabel.setText("Added " + name);
        refresh();
    }

    @FXML
    private void handleRemoveStaff() {
        StaffMember s = staffTable.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(s)) return;
        if (!Ui.confirm("Remove " + s.getName() + " from the staff list?")) return;
        store.getStaff().remove(s);
        Session.log("Removed staff member " + s.getName());
        refresh();
    }
}
