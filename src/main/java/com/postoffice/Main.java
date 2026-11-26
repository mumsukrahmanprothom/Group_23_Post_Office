package com.postoffice;

import com.postoffice.util.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneNavigator.switchTo(stage, "/com/postoffice/login.fxml",
                "Post Office Simulation - Login", 420, 380);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
