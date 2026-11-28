package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CustomerRequestController {

    private ArrayList<CustomerRequest> requests;

    @FXML
    private TextField requestIdField;

    @FXML
    private TextField detailsField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField statusField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        requests = DataStore.getInstance().getRequestRecords();
    }

    @FXML
    protected void saveRequest() {

        CustomerRequest request = new CustomerRequest(
                requestIdField.getText(),
                detailsField.getText(),
                dateField.getText(),
                statusField.getText()
        );

        requests.add(request);

        messageLabel.setText("Request Saved Successfully!");

        requestIdField.clear();
        detailsField.clear();
        dateField.clear();
        statusField.clear();
    }

    @FXML
    protected void viewRequestList() {

        String requestList = "";

        for (CustomerRequest request : requests) {
            requestList += "Request ID: " + request.getRequestId() + "\n";
            requestList += "Details: " + request.getRequestDetails() + "\n";
            requestList += "Date: " + request.getRequestDate() + "\n";
            requestList += "Status: " + request.getStatus() + "\n\n";
        }

        if (requests.isEmpty()) {
            requestList = "No requests available.";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Request List");
        alert.setHeaderText("Saved Customer Requests");
        alert.setContentText(requestList);
        alert.showAndWait();
    }
}
