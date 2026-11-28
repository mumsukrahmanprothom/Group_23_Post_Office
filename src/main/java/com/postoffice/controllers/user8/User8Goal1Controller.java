package com.postoffice.controllers.user8;

import javafx.event.ActionEvent;
import com.postoffice.Main;
import com.postoffice.controllers.GoalController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

/** User 8 Goal 1: Collect & Receive Mail. */
public class User8Goal1Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> mailTable;
    @FXML private TableColumn<String, String> mailIdColumn;
    @FXML private TableColumn<String, String> mailTypeColumn;
    @FXML private TableColumn<String, String> senderNameColumn;
    @FXML private TableColumn<String, String> senderAddressColumn;
    @FXML private TableColumn<String, String> recipientNameColumn;
    @FXML private TableColumn<String, String> recipientAddressColumn;
    @FXML private TableColumn<String, String> weightColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnCollect;
    @FXML private RadioButton radioBtnReceive;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Collect assigned letters and parcels and receive them from the post office for delivery.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user7/user7goal8.fxml"); }
    @FXML private void onNext() { Main.showScene("user8/user8goal2.fxml"); }
}
