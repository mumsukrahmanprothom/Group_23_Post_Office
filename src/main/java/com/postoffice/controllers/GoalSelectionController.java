package com.postoffice.controllers;

import com.postoffice.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GoalSelectionController {
    @FXML
    private void openGoal(ActionEvent event) {
        Button button = (Button) event.getSource();
        Main.showScene(button.getId());
    }
}
