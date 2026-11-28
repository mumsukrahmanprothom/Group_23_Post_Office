package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Optional;

public class CSODashboardController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    public void showDashboard() {
        showMessage("Welcome to Customer Service Dashboard");
    }

    @FXML
    public void showCustomers() {
        loadPage("customer-view.fxml");
    }

    @FXML
    public void showComplaints() {
        loadPage("complaint-view.fxml");
    }

    @FXML
    public void openRequestManagement() {
        loadPage("request-view.fxml");
    }

    @FXML
    public void openPostalServices() {
        loadPage("postal-service-view.fxml");
    }

    @FXML
    public void searchMail() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mail Search");
        dialog.setHeaderText("Search Registered Mail");
        dialog.setContentText("Enter Tracking ID:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            String trackingId = result.get();

            MROMailRecord mail = DataStore.getInstance().findMail(trackingId);

            if (mail != null) {

                String mailInfo = "Tracking ID: " + mail.getTrackingId() + "\n"
                        + "Sender: " + mail.getSender() + "\n"
                        + "Receiver: " + mail.getReceiver() + "\n"
                        + "Mail Type: " + mail.getMailType() + "\n"
                        + "Weight: " + mail.getWeight() + "\n"
                        + "Fee: " + mail.getFee() + "\n"
                        + "Status: " + mail.getStatus() + "\n"
                        + "Registration Date: " + mail.getRegistrationDate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mail Search Result");
                alert.setHeaderText("Mail Found");
                alert.setContentText(mailInfo);
                alert.showAndWait();

            } else {
                showAlert("Mail Search", "No mail found with this Tracking ID.");
            }
        }
    }

    @FXML
    public void generateServiceReceipt() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Service Receipt");
        dialog.setHeaderText("Generate Mail Service Receipt");
        dialog.setContentText("Enter Tracking ID:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            MROMailRecord mail = DataStore.getInstance().findMail(result.get());

            if (mail != null) {

                String receipt = "POST OFFICE SERVICE RECEIPT\n\n"
                        + "Tracking ID: " + mail.getTrackingId() + "\n"
                        + "Sender: " + mail.getSender() + "\n"
                        + "Receiver: " + mail.getReceiver() + "\n"
                        + "Mail Type: " + mail.getMailType() + "\n"
                        + "Weight: " + mail.getWeight() + "\n"
                        + "Service Fee: " + mail.getFee() + "\n"
                        + "Status: " + mail.getStatus() + "\n"
                        + "Date: " + mail.getRegistrationDate() + "\n\n"
                        + "Thank you for using our postal service.";

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Service Receipt");
                alert.setHeaderText("Mail Receipt");
                alert.setContentText(receipt);
                alert.showAndWait();

            } else {
                showAlert("Service Receipt", "Mail not found. Please check Tracking ID.");
            }
        }
    }

    @FXML
    public void customerFeedback() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Customer Feedback");
        dialog.setHeaderText("Submit Customer Feedback");
        dialog.setContentText("Enter Feedback:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            String feedback = result.get();

            if (!feedback.trim().isEmpty()) {

                DataStore.getInstance().getFeedbackRecords().add(feedback);

                showAlert("Customer Feedback", "Feedback Saved Successfully!");

            } else {
                showAlert("Customer Feedback", "Please enter feedback.");
            }
        }
    }

    @FXML
    public void generateCustomerReport() {

        DataStore data = DataStore.getInstance();

        String report = "CUSTOMER SERVICE REPORT\n\n"
                + "Total Customers: " + data.getCustomerRecords().size() + "\n"
                + "Total Customer Requests: " + data.getRequestRecords().size() + "\n"
                + "Total Complaints: " + data.getComplaintRecords().size() + "\n"
                + "Total Registered Mails: " + data.getMailRecords().size() + "\n"
                + "Total Customer Feedback: " + data.getFeedbackRecords().size();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Service Report");
        alert.setHeaderText("Customer Service Summary");
        alert.setContentText(report);
        alert.showAndWait();
    }

    private void loadPage(String fileName) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fileName)
            );

            Parent page = loader.load();

            contentArea.getChildren().setAll(page);

            AnchorPane.setTopAnchor(page, 0.0);
            AnchorPane.setBottomAnchor(page, 0.0);
            AnchorPane.setLeftAnchor(page, 0.0);
            AnchorPane.setRightAnchor(page, 0.0);

        } catch (IOException | NullPointerException e) {

            showMessage("Unable to open page: " + fileName);
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {

        Label label = new Label(message);

        label.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold;"
        );

        contentArea.getChildren().setAll(label);

        AnchorPane.setTopAnchor(label, 35.0);
        AnchorPane.setLeftAnchor(label, 30.0);
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
