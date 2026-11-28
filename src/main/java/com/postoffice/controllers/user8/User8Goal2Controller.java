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

/** User 8 Goal 2: Receive at Counter. */
public class User8Goal2Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> counterTable;
    @FXML private TableColumn<String, String> receiptIdColumn;
    @FXML private TableColumn<String, String> mailTypeColumn;
    @FXML private TableColumn<String, String> senderNameColumn;
    @FXML private TableColumn<String, String> senderAddressColumn;
    @FXML private TableColumn<String, String> weightColumn;
    @FXML private TableColumn<String, String> serviceColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnReceive;
    @FXML private RadioButton radioBtnAtCounter;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Receive letters and parcels from customers at the post office counter.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user8/user8goal1.fxml"); }
    @FXML private void onNext() { Main.showScene("user8/user8goal3.fxml"); }
}
