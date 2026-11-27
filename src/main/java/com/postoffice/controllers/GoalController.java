package com.postoffice.controllers;

import com.postoffice.Main;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;

public abstract class GoalController {
    private Map<String, Object> fxmlNamespace;

    public final void loadGoalData(Map<String, Object> namespace) throws IOException {
        fxmlNamespace = namespace;
        Properties saved = new Properties();
        Path file = getSaveFile();
        if (Files.exists(file)) {
            try (InputStream input = Files.newInputStream(file)) {
                saved.load(input);
            }
        }

        for (Map.Entry<String, Object> entry : namespace.entrySet()) {
            if (entry.getValue() instanceof Node node) {
                restoreControl(saved, entry.getKey(), node);
            }
        }
    }

    @FXML
    public final void onSave(ActionEvent event) {
        try {
            Properties saved = new Properties();
            for (Map.Entry<String, Object> entry : fxmlNamespace.entrySet()) {
                if (entry.getValue() instanceof Node node) {
                    storeControl(saved, entry.getKey(), node);
                }
            }

            Path file = getSaveFile();
            Files.createDirectories(file.getParent());
            try (OutputStream output = Files.newOutputStream(file)) {
                saved.store(output, "Saved data for " + getClass().getSimpleName());
            }
            new Alert(Alert.AlertType.INFORMATION, "This goal's data has been saved.").showAndWait();
        } catch (IOException exception) {
            new Alert(Alert.AlertType.ERROR, "Could not save this goal's data: " + exception.getMessage())
                    .showAndWait();
        }
    }

    @FXML
    public final void onLogout() {
        Main.showScene("main.fxml");
    }

    private Path getSaveFile() {
        return Path.of(System.getProperty("user.home"), ".post-office", "goal-data",
                getClass().getSimpleName() + ".properties");
    }

    private static void storeControl(Properties saved, String id, Node node) {
        if (node instanceof TextInputControl input) {
            saved.setProperty(id + ".text", input.getText());
        } else if (node instanceof DatePicker datePicker) {
            saved.setProperty(id + ".value", datePicker.getValue() == null ? "" : datePicker.getValue().toString());
        } else if (node instanceof ToggleButton toggleButton) {
            saved.setProperty(id + ".selected", Boolean.toString(toggleButton.isSelected()));
        } else if (node instanceof ListView<?> listView) {
            saved.setProperty(id + ".count", Integer.toString(listView.getItems().size()));
            for (int index = 0; index < listView.getItems().size(); index++) {
                saved.setProperty(id + "." + index, String.valueOf(listView.getItems().get(index)));
            }
        } else if (node instanceof TableView<?> tableView) {
            saved.setProperty(id + ".count", Integer.toString(tableView.getItems().size()));
            for (int index = 0; index < tableView.getItems().size(); index++) {
                saved.setProperty(id + "." + index, String.valueOf(tableView.getItems().get(index)));
            }
        } else if (node instanceof ComboBox<?> comboBox && comboBox.getValue() != null) {
            saved.setProperty(id + ".value", String.valueOf(comboBox.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    private static void restoreControl(Properties saved, String id, Node node) {
        if (node instanceof TextInputControl input) {
            input.setText(saved.getProperty(id + ".text", ""));
        } else if (node instanceof DatePicker datePicker) {
            String value = saved.getProperty(id + ".value", "");
            if (!value.isEmpty()) {
                datePicker.setValue(java.time.LocalDate.parse(value));
            }
        } else if (node instanceof ToggleButton toggleButton) {
            toggleButton.setSelected(Boolean.parseBoolean(saved.getProperty(id + ".selected", "false")));
        } else if (node instanceof ListView<?> listView) {
            restoreItems(saved, id, (ListView<Object>) listView);
        } else if (node instanceof TableView<?> tableView) {
            restoreItems(saved, id, (TableView<Object>) tableView);
        } else if (node instanceof ComboBox<?> comboBox) {
            String value = saved.getProperty(id + ".value");
            if (value != null && comboBox.getItems().contains(value)) {
                ((ComboBox<Object>) comboBox).setValue(value);
            }
        }
    }

    private static void restoreItems(Properties saved, String id, ListView<Object> listView) {
        listView.getItems().setAll(readItems(saved, id));
    }

    private static void restoreItems(Properties saved, String id, TableView<Object> tableView) {
        tableView.getItems().setAll(readItems(saved, id));
    }

    private static java.util.List<String> readItems(Properties saved, String id) {
        int count = Integer.parseInt(saved.getProperty(id + ".count", "0"));
        java.util.List<String> items = new java.util.ArrayList<>(count);
        for (int index = 0; index < count; index++) {
            items.add(saved.getProperty(id + "." + index, ""));
        }
        return items;
    }
}
