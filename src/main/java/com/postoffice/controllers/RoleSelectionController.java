package com.postoffice.controllers;

import com.postoffice.Main;
import javafx.fxml.FXML;

public class RoleSelectionController {
    @FXML
    private void openPostOfficeManager() {
        Main.showScene("user7/menu.fxml");
    }

    @FXML
    private void openPostalWorker() {
        Main.showScene("user8/menu.fxml");
    }
}
