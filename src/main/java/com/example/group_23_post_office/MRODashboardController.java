package com.example.group_23_post_office;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;

public class MRODashboardController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    private TextField senderTextField;

    @FXML
    private TextField receiverTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private ComboBox<String> mailTypeComboBox;

    @FXML
    private TextField trackingIdTextField;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TableView<MROMailRecord> mailTableView;

    @FXML
    private TableColumn<MROMailRecord, String> trackingIdTableColumn;

    @FXML
    private TableColumn<MROMailRecord, String> senderTableColumn;

    @FXML
    private TableColumn<MROMailRecord, String> receiverTableColumn;

    @FXML
    private TableColumn<MROMailRecord, String> mailTypeTableColumn;

    @FXML
    private TableColumn<MROMailRecord, Double> weightTableColumn;

    @FXML
    private TableColumn<MROMailRecord, Double> feeTableColumn;

    @FXML
    private TableColumn<MROMailRecord, String> statusTableColumn;

    @FXML
    private TableColumn<MROMailRecord, String> dateTableColumn;

    @FXML
    private Label messageLabel;

    private ObservableList<MROMailRecord> mailList;

    @FXML
    public void initialize() {

        mailList = DataStore.getInstance().getMailRecords();

        mailTypeComboBox.getItems().addAll(
                "Regular Mail",
                "Express Mail",
                "Registered Mail"
        );

        statusComboBox.getItems().addAll(
                "Registered",
                "In Transit",
                "Delivered",
                "Returned"
        );

        trackingIdTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("trackingId")
        );

        senderTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("sender")
        );

        receiverTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("receiver")
        );

        mailTypeTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("mailType")
        );

        weightTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, Double>("weight")
        );

        feeTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, Double>("fee")
        );

        statusTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("status")
        );

        dateTableColumn.setCellValueFactory(
                new PropertyValueFactory<MROMailRecord, String>("registrationDate")
        );
    }

    @FXML
    public void registerMailButtonOnAction(ActionEvent actionEvent) {

        if (senderTextField.getText().isEmpty()
                || receiverTextField.getText().isEmpty()
                || weightTextField.getText().isEmpty()
                || mailTypeComboBox.getValue() == null) {

            messageLabel.setText("Please fill in all fields.");
            return;
        }

        try {

            double weight = Double.parseDouble(weightTextField.getText());

            if (weight <= 0) {
                messageLabel.setText("Weight must be greater than zero.");
                return;
            }

            double fee = getServiceFee(mailTypeComboBox.getValue());

            String trackingId =
                    DataStore.getInstance().generateTrackingId();

            MROMailRecord mail = new MROMailRecord(
                    trackingId,
                    senderTextField.getText(),
                    receiverTextField.getText(),
                    mailTypeComboBox.getValue(),
                    weight,
                    fee,
                    "Registered",
                    LocalDate.now().toString()
            );

            DataStore.getInstance().addMail(mail);

            mailTableView.getItems().clear();
            mailTableView.getItems().addAll(mailList);

            messageLabel.setText(
                    "Mail registered successfully. Tracking ID: " + trackingId
            );

            senderTextField.clear();
            receiverTextField.clear();
            weightTextField.clear();
            mailTypeComboBox.setValue(null);

        } catch (NumberFormatException e) {

            messageLabel.setText("Enter a valid weight.");
        }
    }

    @FXML
    public void searchMailButtonOnAction(ActionEvent actionEvent) {

        String trackingId = trackingIdTextField.getText().trim();

        MROMailRecord foundMail =
                DataStore.getInstance().findMail(trackingId);

        if (foundMail == null) {

            messageLabel.setText("Mail not found.");

        } else {

            messageLabel.setText(
                    "Tracking ID: " + foundMail.getTrackingId()
                            + "\nSender: " + foundMail.getSender()
                            + "\nReceiver: " + foundMail.getReceiver()
                            + "\nMail Type: " + foundMail.getMailType()
                            + "\nWeight: " + foundMail.getWeight()
                            + "\nFee: " + foundMail.getFee()
                            + "\nStatus: " + foundMail.getStatus()
                            + "\nDate: " + foundMail.getRegistrationDate()
            );
        }
    }

    @FXML
    public void showRegisteredMailButtonOnAction(ActionEvent actionEvent) {

        mailTableView.getItems().clear();

        if (mailList.isEmpty()) {

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("No registered mail available.");
            a.showAndWait();

        } else {

            for (MROMailRecord mail : mailList) {
                mailTableView.getItems().add(mail);
            }
        }
    }

    @FXML
    public void updateMailStatusButtonOnAction(ActionEvent actionEvent) {

        String trackingId = trackingIdTextField.getText().trim();

        if (statusComboBox.getValue() == null) {

            messageLabel.setText("Please select a new status.");
            return;
        }

        MROMailRecord mail =
                DataStore.getInstance().findMail(trackingId);

        if (mail != null) {

            mail.setStatus(statusComboBox.getValue());

            mailTableView.getItems().clear();
            mailTableView.getItems().addAll(mailList);

            messageLabel.setText("Mail status updated successfully.");

        } else {

            messageLabel.setText("Mail not found.");
        }
    }

    @FXML
    public void cancelMailRegistrationButtonOnAction(ActionEvent actionEvent) {

        String trackingId = trackingIdTextField.getText().trim();

        MROMailRecord mail =
                DataStore.getInstance().findMail(trackingId);

        if (mail != null) {

            DataStore.getInstance().removeMail(mail);

            mailTableView.getItems().clear();
            mailTableView.getItems().addAll(mailList);

            messageLabel.setText("Mail registration cancelled.");

        } else {

            messageLabel.setText("Mail not found.");
        }
    }

    @FXML
    public void calculateMailFeeButtonOnAction(ActionEvent actionEvent) {

        if (mailTypeComboBox.getValue() == null) {

            messageLabel.setText("Please select a mail type.");
            return;
        }

        double fee = getServiceFee(mailTypeComboBox.getValue());

        messageLabel.setText("Mail Fee: " + fee + " Taka");
    }

    private double getServiceFee(String mailType) {

        if (mailType.equals("Regular Mail")) {
            return 20.0;

        } else if (mailType.equals("Express Mail")) {
            return 50.0;

        } else if (mailType.equals("Registered Mail")) {
            return 40.0;
        }

        return 0.0;
    }

    @FXML
    public void generateMailReceiptButtonOnAction(ActionEvent actionEvent) {

        String trackingId = trackingIdTextField.getText().trim();

        MROMailRecord mail =
                DataStore.getInstance().findMail(trackingId);

        if (mail != null) {

            String receipt =
                    "POST OFFICE RECEIPT\n\n"
                            + "Tracking ID: " + mail.getTrackingId() + "\n"
                            + "Sender: " + mail.getSender() + "\n"
                            + "Receiver: " + mail.getReceiver() + "\n"
                            + "Mail Type: " + mail.getMailType() + "\n"
                            + "Weight: " + mail.getWeight() + "\n"
                            + "Fee: " + mail.getFee() + " Taka\n"
                            + "Status: " + mail.getStatus() + "\n"
                            + "Date: " + mail.getRegistrationDate();

            showAlert("Mail Receipt", receipt);

        } else {

            messageLabel.setText("Mail not found.");
        }
    }

    @FXML
    public void generateMailReportButtonOnAction(ActionEvent actionEvent) {

        String report = "MAIL REGISTRATION REPORT\n\n";

        int totalMail = 0;
        double totalFee = 0;

        for (MROMailRecord mail : mailList) {

            totalMail++;
            totalFee = totalFee + mail.getFee();

            report += "Tracking ID: " + mail.getTrackingId() + "\n";
            report += "Sender: " + mail.getSender() + "\n";
            report += "Receiver: " + mail.getReceiver() + "\n";
            report += "Status: " + mail.getStatus() + "\n\n";
        }

        report += "Total Registered Mail: " + totalMail + "\n";
        report += "Total Service Fee: " + totalFee + " Taka";

        showAlert("Mail Report", report);
    }

    @FXML
    public void openPostalServices(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("postal-service-view.fxml")
            );

            Parent page = loader.load();

            contentArea.getChildren().setAll(page);

        } catch (IOException e) {

            showAlert("Error", "Unable to open Postal Services.");
        }
    }

    private void showAlert(String title, String message) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(message);
        a.showAndWait();
    }
}
