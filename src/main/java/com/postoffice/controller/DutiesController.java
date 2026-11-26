package com.postoffice.controller;

import com.postoffice.model.Duty;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Dates;
import com.postoffice.util.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DutiesController {
    @FXML private TableView<Duty> table;
    @FXML private ComboBox<String> filterCombo;
    @FXML private ComboBox<String> staffCombo;
    @FXML private TextField taskField;
    @FXML private TextField dateField;
    @FXML private Button removeBtn;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();
    private final ObservableList<Duty> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dateField.setText(Dates.today());
        removeBtn.setDisable(!Session.isPostMaster());

        filterCombo.getItems().addAll("All duties", "Today", "Not finished");
        filterCombo.getSelectionModel().selectFirst();

        table.setItems(shown);
        refresh();
    }

    public void refresh() {
        String selected = staffCombo.getValue();
        staffCombo.getItems().setAll(store.getStaffNames());
        if (selected != null && staffCombo.getItems().contains(selected)) staffCombo.setValue(selected);
        else staffCombo.getSelectionModel().selectFirst();
        applyFilter();
        table.refresh();
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    private void applyFilter() {
        String f = filterCombo.getValue();
        if (f == null) f = "All duties";
        Duty selectedBefore = table.getSelectionModel().getSelectedItem();
        shown.clear();
        for (Duty d : store.getDuties()) {
            boolean show = true;
            if (f.equals("Today")) show = d.getDate().equals(Dates.today());
            else if (f.equals("Not finished")) show = !Duty.DONE.equals(d.getStatus());
            if (show) shown.add(d);
        }
        if (selectedBefore != null && shown.contains(selectedBefore)) {
            table.getSelectionModel().select(selectedBefore);
        }
    }

    @FXML
    private void handleAssign() {
        String staff = staffCombo.getValue();
        String task = taskField.getText().trim();
        String date = dateField.getText().trim();
        if (staff == null || task.isEmpty() || date.isEmpty()) {
            statusLabel.setText("Choose a staff member and enter the task and date (yyyy-MM-dd).");
            return;
        }
        if (!Dates.looksLikeDate(date)) {
            statusLabel.setText("Date must look like 2026-12-31.");
            return;
        }
        Duty d = new Duty(store.nextId("DT"), staff, task, date, Session.username());
        store.getDuties().add(0, d);
        Session.log("Assigned duty '" + task + "' to " + staff);
        statusLabel.setText("Assigned " + d.getId() + " to " + staff);
        taskField.clear();
        refresh();
    }

    @FXML
    private void handleInProgress() { setStatus(Duty.IN_PROGRESS); }

    @FXML
    private void handleDone() { setStatus(Duty.DONE); }

    private void setStatus(String status) {
        Duty d = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(d)) return;
        d.setStatus(status);
        Session.log("Set duty " + d.getId() + " (" + d.getStaff() + ") to '" + status + "'");
        refresh();
    }

    @FXML
    private void handleRemove() {
        Duty d = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(d)) return;
        if (!Ui.confirm("Remove duty " + d.getId() + "?")) return;
        store.getDuties().remove(d);
        Session.log("Removed duty " + d.getId());
        refresh();
    }
}
