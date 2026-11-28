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

/** User 8 Goal 6: Pack & Seal Mail. */
public class User8Goal6Controller extends GoalController {
    @FXML private Label appTitleLabel;
    @FXML private Label goalTitleLabel;
    @FXML private Label taskLabel;
    @FXML private TextArea taskField;
    @FXML private Button btnRecordTask;
    @FXML private ListView<String> taskListView;
    @FXML private TableView<String> packTable;
    @FXML private TableColumn<String, String> mailIdColumn;
    @FXML private TableColumn<String, String> parcelTypeColumn;
    @FXML private TableColumn<String, String> packingMaterialColumn;
    @FXML private TableColumn<String, String> sealNumberColumn;
    @FXML private TableColumn<String, String> dateColumn;
    @FXML private TableColumn<String, String> statusColumn;
    @FXML private DatePicker processDate;
    @FXML private RadioButton radioBtnPack;
    @FXML private RadioButton radioBtnSeal;
    @FXML private Label statusLabel;
    @FXML private Button btnBack;
    @FXML private Button btnNext;

    @FXML
    private void initialize() {
        taskLabel.setText("Task: Pack and seal letters and parcels for safe delivery.");
    }

    @FXML
    private void onRecordTask(ActionEvent actionEvent) {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListView.getItems().add(task);
            taskField.clear();
        }
    }

    @FXML private void onBack() { Main.showScene("user8/user8goal5.fxml"); }
    @FXML private void onNext() { Main.showScene("user8/user8goal7.fxml"); }
}
