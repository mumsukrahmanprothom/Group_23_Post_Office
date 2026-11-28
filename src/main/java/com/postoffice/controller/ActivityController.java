package com.postoffice.controller;

import com.postoffice.model.ActivityEntry;
import com.postoffice.model.PostMaster;
import com.postoffice.model.User;
import com.postoffice.service.ActivityLogger;
import com.postoffice.service.Session;
import com.postoffice.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ActivityController {
    private static final String ALL_USERS = "All Users";

    @FXML private TableView<ActivityEntry> table;
    @FXML private ComboBox<String> userFilter;
    @FXML private TextField searchField;
    @FXML private Label countLabel;

    private final ObservableList<ActivityEntry> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        table.setItems(shown);
        refresh();
    }

    public void refresh() {
        String selectedUser = userFilter.getValue();
        userFilter.getItems().clear();
        userFilter.getItems().add(ALL_USERS);
        for (User u : UserService.getInstance().getAllUsers()) {
            if (Session.isPostMaster() || !(u instanceof PostMaster)) {
                userFilter.getItems().add(u.getUsername());
            }
        }
        if (selectedUser != null && userFilter.getItems().contains(selectedUser)) {
            userFilter.setValue(selectedUser);
        } else {
            userFilter.getSelectionModel().selectFirst();
        }
        applyFilter();
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    private void applyFilter() {
        String user = userFilter.getValue();
        String text = "";
        if (searchField.getText() != null) text = searchField.getText().trim().toLowerCase();
        boolean pm = Session.isPostMaster();

        shown.clear();
        for (ActivityEntry e : ActivityLogger.getInstance().getEntries()) {
            if (!pm && PostMaster.ROLE.equals(e.getRole())) continue;
            if (user != null && !user.equals(ALL_USERS) && !e.getUsername().equals(user)) continue;
            if (!text.isEmpty()
                    && !e.getAction().toLowerCase().contains(text)
                    && !e.getUsername().toLowerCase().contains(text)) continue;
            shown.add(e);
        }
        countLabel.setText(shown.size() + " entries");
    }

    @FXML
    private void handleClear() {
        userFilter.getSelectionModel().selectFirst();
        searchField.clear();
        applyFilter();
    }
}
