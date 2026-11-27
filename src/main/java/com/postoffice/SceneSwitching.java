/* <license-header /> */
package com.postoffice;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Scene switching utility class (from the Bangladesh Football Federation demo). */
public class SceneSwitching {
    /** Switch the main window to the given FXML scene. */
    public void sceneSwitchingWithoutDataPassing(Stage stage, String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** Switch to a new independent window. */
    public void windowSwitchingWithoutDataPassing(String sceneName, Stage parentStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
        parentStage.hide();
    }

    /** Log out by switching back to the LoginScene. */
    public void logOutSceneSwitching(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/postoffice/fxml/user7/user7goal1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}