package com.postoffice.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class Ui {
    public static void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    public static void warn(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.showAndWait();
    }

    public static boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public static String ask(String title, String prompt, String defaultValue) {
        if (defaultValue == null) defaultValue = "";
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(prompt);
        dialog.showAndWait();
        String answer = dialog.getResult();
        if (answer == null) return null;
        answer = answer.trim();
        if (answer.isEmpty()) return null;
        return answer;
    }

    public static boolean noSelection(Object selectedItem) {
        if (selectedItem == null) {
            warn("Please select a row first.");
            return true;
        }
        return false;
    }

    public static Double parseAmount(String text) {
        try {
            double d = Double.parseDouble(text.trim());
            if (d > 0) return d;
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String money(double value) {
        long cents = Math.round(value * 100);
        boolean negative = cents < 0;
        if (negative) cents = -cents;
        long whole = cents / 100;
        long fraction = cents % 100;
        String fractionText = String.valueOf(fraction);
        if (fraction < 10) fractionText = "0" + fractionText;
        String result = whole + "." + fractionText;
        if (negative) result = "-" + result;
        return result;
    }
}
