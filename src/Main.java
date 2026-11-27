package com.postoffice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Application entry point. Loads the first scene and handles scene switching. */
public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showScene("user7goal1.fxml");
        primaryStage.setTitle("Simulating Operations of a Post Office");
        primaryStage.setMaxWidth(1100);
        primaryStage.setMaxHeight(720);
        primaryStage.show();
    }

    /** Switches the main window to the given FXML scene. */
    public static void showScene(String fileName) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/com/postoffice/fxml/" + fileName));
            primaryStage.setScene(new Scene(root));
            primaryStage.sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
