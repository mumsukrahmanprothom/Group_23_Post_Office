package com.postoffice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Utility class for scene switching. Not an Application subclass.
 *  All scene switching goes through Main.showScene() which holds the primaryStage. */
public class PostOfficeApp {
    /** Switches the main window to the given FXML scene. */
    public static void showScene(String fileName) {
        try {
            Parent root = FXMLLoader.load(PostOfficeApp.class.getResource("/com/postoffice/fxml/" + fileName));
            Stage stage = Main.getPrimaryStage();
            if (stage != null) {
                stage.setScene(new Scene(root));
                stage.sizeToScene();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
