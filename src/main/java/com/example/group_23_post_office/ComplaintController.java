package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ComplaintController {

    private ArrayList<Complaint> complaintList;

    @FXML
    private TextField complaintIdField;

    @FXML
    private TextField detailsField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField resolutionField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        complaintList = DataStore.getInstance().getComplaintRecords();
    }

    @FXML
    public void saveComplaint() {

        Complaint complaint = new Complaint(
                complaintIdField.getText(),
                detailsField.getText(),
                statusField.getText(),
                resolutionField.getText()
        );

        complaintList.add(complaint);

        messageLabel.setText("Complaint Saved Successfully!");

        complaintIdField.clear();
        detailsField.clear();
        statusField.clear();
        resolutionField.clear();
    }

    @FXML
    public void viewComplaintList() {

        String complaintInfo = "";

        for (Complaint complaint : complaintList) {
            complaintInfo += "Complaint ID: " + complaint.getComplaintId() + "\n";
            complaintInfo += "Details: " + complaint.getComplaintDetails() + "\n";
            complaintInfo += "Status: " + complaint.getStatus() + "\n";
            complaintInfo += "Resolution: " + complaint.getResolutionNote() + "\n\n";
        }

        if (complaintList.isEmpty()) {
            complaintInfo = "No Complaints Available.";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complaint List");
        alert.setHeaderText("Saved Complaints");
        alert.setContentText(complaintInfo);
        alert.showAndWait();
    }
}
