package com.postoffice.controller;

import com.postoffice.model.FinanceRecord;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Dates;
import com.postoffice.util.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FinanceController {
    @FXML private TableView<FinanceRecord> table;
    @FXML private ComboBox<String> filterCombo;
    @FXML private Label todayLabel;
    @FXML private Label totalLabel;
    @FXML private HBox expenseBox;
    @FXML private TextField categoryField;
    @FXML private TextField amountField;
    @FXML private TextField descriptionField;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();
    private final ObservableList<FinanceRecord> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        boolean pm = Session.isPostMaster();
        expenseBox.setVisible(pm);
        expenseBox.setManaged(pm);

        filterCombo.getItems().addAll("Today", "All", "Revenue only", "Expenses only");
        filterCombo.getSelectionModel().selectFirst();

        table.setItems(shown);
        refresh();
    }

    public void refresh() {
        applyFilter();
        table.refresh();
        String today = Dates.today();
        double rev = store.total(FinanceRecord.REVENUE, today);
        double exp = store.total(FinanceRecord.EXPENSE, today);
        todayLabel.setText("Today:  revenue " + Ui.money(rev) + "   expenses " + Ui.money(exp)
                + "   net " + Ui.money(rev - exp));
        double r = store.total(FinanceRecord.REVENUE, null);
        double e = store.total(FinanceRecord.EXPENSE, null);
        totalLabel.setText("All time:  revenue " + Ui.money(r) + "   expenses " + Ui.money(e)
                + "   net " + Ui.money(r - e));
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    private void applyFilter() {
        String f = filterCombo.getValue();
        if (f == null) f = "Today";
        shown.clear();
        for (FinanceRecord r : store.getFinance()) {
            boolean show = true;
            if (f.equals("Today")) show = r.getDate().equals(Dates.today());
            else if (f.equals("Revenue only")) show = FinanceRecord.REVENUE.equals(r.getType());
            else if (f.equals("Expenses only")) show = FinanceRecord.EXPENSE.equals(r.getType());
            if (show) shown.add(r);
        }
    }

    @FXML
    private void handleAddExpense() {
        if (!Session.isPostMaster()) return;
        String category = categoryField.getText().trim();
        String desc = descriptionField.getText().trim();
        Double amount = Ui.parseAmount(amountField.getText());
        if (category.isEmpty() || amount == null) {
            statusLabel.setText("Enter a category and a valid amount.");
            return;
        }
        store.addExpense(category, amount, desc);
        Session.log("Recorded expense: " + category + " " + Ui.money(amount));
        statusLabel.setText("Expense recorded.");
        categoryField.clear();
        amountField.clear();
        descriptionField.clear();
        refresh();
    }
}
