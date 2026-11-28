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

/** User 8 Goal 7: Delivery Schedule. */
public class User8Goal7Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> scheduleTable;
    @FXML private TableColumn<String, String> parcelIdColumn;
    @FXML private TableColumn<String, String> destinationColumn;
    @FXML private TableColumn<String, String> deliveryDateColumn;
    @FXML private TableColumn<String, String> timeColumn;
    @FXML private TableColumn<String, String> carrierColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnSchedule;
    @FXML private RadioButton radioBtnRoute;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Maintain the post office delivery schedule and route plan.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user8/user8goal6.fxml"); }
    @FXML private void onNext() { Main.showScene("user8/user8goal8.fxml"); }
}
