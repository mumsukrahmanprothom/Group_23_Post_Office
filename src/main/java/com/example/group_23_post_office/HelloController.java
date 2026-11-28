
package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    public void onCSOClick() {
        welcomeText.setText("Customer Service Officer Dashboard");
    }

    @FXML
    public void onMROClick() {
        welcomeText.setText("Mail Registration Officer Dashboard");
    }

    @FXML
    public void openCustomerForm(ActionEvent event) throws IOException {

        FXMLLoader myFxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("customer-view.fxml"));

        Scene nextScene = new Scene(myFxmlLoader.load());

        Stage currentStage = (Stage)((Node) event.getSource())
                .getScene().getWindow();

        currentStage.setScene(nextScene);
        currentStage.setTitle("Manage Customer Information");
        currentStage.show();
    }

    @FXML
    public void openRequestForm(ActionEvent event) throws IOException {

        FXMLLoader myFxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("request-view.fxml"));

        Scene nextScene = new Scene(myFxmlLoader.load());

        Stage currentStage = (Stage)((Node) event.getSource())
                .getScene().getWindow();

        currentStage.setScene(nextScene);
        currentStage.setTitle("Register Customer Request");
        currentStage.show();
    }

    @FXML
    public void openComplaintForm(ActionEvent event) throws IOException {

        FXMLLoader myFxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("complaint-view.fxml"));

        Scene nextScene = new Scene(myFxmlLoader.load());

        Stage currentStage = (Stage)((Node) event.getSource())
                .getScene().getWindow();

        currentStage.setScene(nextScene);
        currentStage.setTitle("Handle Customer Complaints");
        currentStage.show();
    }

    @FXML
    public void openPostalServiceForm(ActionEvent event) throws IOException {

        FXMLLoader myFxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("postal-service-view.fxml"));

        Scene nextScene = new Scene(myFxmlLoader.load());

        Stage currentStage = (Stage)((Node) event.getSource())
                .getScene().getWindow();

        currentStage.setScene(nextScene);
        currentStage.setTitle("Provide Postal Service Information");
        currentStage.show();
    }
}
