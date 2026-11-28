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

/** User 7 Goal 3: Outgoing Mail Dispatch. */
public class User7Goal3Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> parcelsTable;
    @FXML private TableColumn<String, String> parcelIdColumn;
    @FXML private TableColumn<String, String> recipientNameColumn;
    @FXML private TableColumn<String, String> recipientAddressColumn;
    @FXML private TableColumn<String, String> weightColumn;
    @FXML private TableColumn<String, String> serviceTypeColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnDispatch;
    @FXML private RadioButton radioBtnNext;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Dispatch outgoing mail from the post office to the recipient's address.");
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
    @FXML private void onNext() { Main.showScene("user7/user7goal4.fxml"); }
}
