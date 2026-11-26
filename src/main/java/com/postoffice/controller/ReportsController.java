package com.postoffice.controller;

import com.postoffice.service.ReportService;
import com.postoffice.service.Session;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;

public class ReportsController {
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextArea reportArea;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        if (Session.isPostMaster()) {
            typeCombo.getItems().addAll(ReportService.MANAGEMENT, ReportService.FINANCIAL,
                    ReportService.DAILY);
        } else {
            typeCombo.getItems().addAll(ReportService.DAILY, ReportService.FINANCIAL);
        }
        typeCombo.getSelectionModel().selectFirst();
        reportArea.setEditable(false);
    }

    @FXML
    private void handleGenerate() {
        String type = typeCombo.getValue();
        reportArea.setText(ReportService.build(type));
        Session.log("Generated " + type);
        statusLabel.setText(type + " generated.");
    }

    @FXML
    private void handleSave() {
        String text = reportArea.getText();
        if (text == null || text.trim().isEmpty()) {
            statusLabel.setText("Generate a report first.");
            return;
        }
        try {
            File file = ReportService.save(typeCombo.getValue(), text);
            Session.log("Saved report " + file.getName());
            statusLabel.setText("Saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            statusLabel.setText("Could not save: " + e.getMessage());
        }
    }
}
