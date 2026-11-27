package com.postoffice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.postoffice.controllers.GoalController;

/** Application entry point. Loads the first scene and handles scene switching. */
public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showScene("main.fxml");
        primaryStage.setTitle("Simulating Operations of a Post Office");
        primaryStage.setMaxWidth(1100);
        primaryStage.setMaxHeight(720);
        primaryStage.show();
    }

    /** Switches the main window to the given FXML scene. */
    public static void showScene(String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/postoffice/fxml/" + fileName));
            Parent root = loader.load();
            if (loader.getController() instanceof GoalController goalController) {
                goalController.loadGoalData(loader.getNamespace());
            }
            primaryStage.setScene(new Scene(root));
            primaryStage.sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /** Returns the primary stage for scene switching from controllers. */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
