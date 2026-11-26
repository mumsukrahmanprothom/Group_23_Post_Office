package com.postoffice.controller;

import com.postoffice.model.AttendanceRecord;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Dates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class AttendanceController {
    @FXML private TableView<AttendanceRecord> table;
    @FXML private ComboBox<String> filterCombo;
    @FXML private ComboBox<String> staffCombo;
    @FXML private ComboBox<String> statusCombo;
    @FXML private Label summaryLabel;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();
    private final ObservableList<AttendanceRecord> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        statusCombo.getItems().addAll(AttendanceRecord.PRESENT, AttendanceRecord.LATE, AttendanceRecord.ABSENT);
        statusCombo.getSelectionModel().selectFirst();

        filterCombo.getItems().addAll("Today", "All records");
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

        int present = 0, late = 0, absent = 0, marked = 0;
        for (AttendanceRecord a : store.getAttendance()) {
            if (!a.getDate().equals(Dates.today())) continue;
            marked++;
            if (AttendanceRecord.PRESENT.equals(a.getStatus())) present++;
            else if (AttendanceRecord.LATE.equals(a.getStatus())) late++;
            else absent++;
        }
        summaryLabel.setText("Today: " + present + " present, " + late + " late, " + absent + " absent  ("
                + marked + " of " + store.getStaff().size() + " staff recorded)");
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    private void applyFilter() {
        boolean onlyToday = !"All records".equals(filterCombo.getValue());
        shown.clear();
        for (AttendanceRecord a : store.getAttendance()) {
            if (!onlyToday || a.getDate().equals(Dates.today())) shown.add(a);
        }
    }

    @FXML
    private void handleRecord() {
        String staff = staffCombo.getValue();
        if (staff == null) {
            statusLabel.setText("Choose a staff member.");
            return;
        }
        String today = Dates.today();
        for (AttendanceRecord a : store.getAttendance()) {
            if (a.getDate().equals(today) && a.getStaff().equals(staff)) {
                a.update(statusCombo.getValue(), Session.username());
                Session.log("Updated attendance of " + staff + " to " + statusCombo.getValue());
                statusLabel.setText("Updated today's record of " + staff);
                refresh();
                return;
            }
        }
        store.getAttendance().add(0, new AttendanceRecord(today, staff, statusCombo.getValue(), Session.username()));
        Session.log("Recorded attendance: " + staff + " " + statusCombo.getValue());
        statusLabel.setText("Recorded " + staff + " as " + statusCombo.getValue());
        refresh();
    }
}
