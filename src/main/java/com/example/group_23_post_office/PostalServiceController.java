package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class PostalServiceController {

    private ArrayList<PostalService> serviceList = new ArrayList<PostalService>();

    @FXML
    public void initialize() {

        serviceList = new ArrayList<PostalService>();

        serviceList.add(new PostalService(
                "S001", "Regular Mail", "Normal postal delivery", true, 20.0
        ));

        serviceList.add(new PostalService(
                "S002", "Express Mail", "Fast postal delivery", true, 50.0
        ));

        serviceList.add(new PostalService(
                "S003", "Registered Mail", "Mail with registration service", true, 40.0
        ));
    }

    @FXML
    public void viewPostalServices() {

        String serviceInfo = "";

        for (PostalService service : serviceList) {

            serviceInfo += "Service ID: " + service.getServiceId() + "\n";
            serviceInfo += "Service Name: " + service.getServiceName() + "\n";
            serviceInfo += "Description: " + service.getDescription() + "\n";
            serviceInfo += "Availability: " + service.isAvailability() + "\n";
            serviceInfo += "Service Fee: " + service.getServiceFee() + "\n\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Postal Services");
        alert.setHeaderText("Available Postal Services");
        alert.setContentText(serviceInfo);
        alert.showAndWait();
    }
}
