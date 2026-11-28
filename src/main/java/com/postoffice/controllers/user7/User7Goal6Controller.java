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

/** User 7 Goal 6: Postal Equipment & Resources. */
public class User7Goal6Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> equipmentTable;
    @FXML private TableColumn<String, String> equipmentIdColumn;
    @FXML private TableColumn<String, String> typeColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private TableColumn<String, String> lastMaintainedColumn;
    @FXML private TableColumn<String, String> reorderColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnCheck;
    @FXML private RadioButton radioBtnMaintain;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Check, maintain and replenish postal equipment and office resources.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user7/user7goal5.fxml"); }
    @FXML private void onNext() { Main.showScene("user7/user7goal7.fxml"); }
}
