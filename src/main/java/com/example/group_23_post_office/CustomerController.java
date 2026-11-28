package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class CustomerController {

    private ArrayList<CSOCustomer> customerList;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        customerList = DataStore.getInstance().getCustomerRecords();
    }

    @FXML
    public void saveCustomer() {

        CSOCustomer customer = new CSOCustomer(
                customerIdField.getText(),
                nameField.getText(),
                phoneField.getText(),
                addressField.getText(),
                emailField.getText()
        );

        try {
            File f = new File("customers.txt");
            FileWriter fw;

            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }

            fw.write(customer.getCustomerId() + ","
                    + customer.getName() + ","
                    + customer.getPhoneNumber() + ","
                    + customer.getAddress() + ","
                    + customer.getEmail() + "\n");

            fw.close();

            customerList.add(customer);

            messageLabel.setText("Customer Saved Successfully!");

            customerIdField.clear();
            nameField.clear();
            phoneField.clear();
            addressField.clear();
            emailField.clear();

        } catch (Exception e) {
            messageLabel.setText("Customer Could Not Be Saved!");
        }
    }

    @FXML
    public void viewCustomerList() {

        String customerInfo = "";

        for (CSOCustomer customer : customerList) {
            customerInfo += "Customer ID: " + customer.getCustomerId() + "\n";
            customerInfo += "Name: " + customer.getName() + "\n";
            customerInfo += "Phone: " + customer.getPhoneNumber() + "\n";
            customerInfo += "Address: " + customer.getAddress() + "\n";
            customerInfo += "Email: " + customer.getEmail() + "\n\n";
        }

        if (customerList.isEmpty()) {
            customerInfo = "No Customers Available.";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer List");
        alert.setHeaderText("Saved Customer Information");
        alert.setContentText(customerInfo);
        alert.showAndWait();
    }
}
