package com.postoffice.controllers.user7;

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

/** User 7 Goal 5: Post Office Opening. */
public class User7Goal5Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> openingTable;
    @FXML private TableColumn<String, String> openingTimeColumn;
    @FXML private TableColumn<String, String> staffColumn;
    @FXML private TableColumn<String, String> countersColumn;
    @FXML private TableColumn<String, String> equipmentColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnOpen;
    @FXML private RadioButton radioBtnForService;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Open the post office and prepare all counters for customer service.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user7/user7goal4.fxml"); }
    @FXML private void onNext() { Main.showScene("user7/user7goal6.fxml"); }
}
