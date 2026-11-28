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

/** User 7 Goal 1: Office Opening & Closing. */
public class User7Goal1Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnReceived;
    @FXML private RadioButton radioBtnSent;
    @FXML private Label statusLabel;
    @FXML private TableView<String> taskTable;
    @FXML private TableColumn<String, String> taskColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Prepare the post office for daily operation at the beginning of the day and properly closing it at the end of the day.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user7/user7goal2.fxml"); }
    @FXML private void onNext() { Main.showScene("user7/user7goal3.fxml"); }
}
