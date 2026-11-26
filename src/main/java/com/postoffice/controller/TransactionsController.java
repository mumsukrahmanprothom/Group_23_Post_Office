package com.postoffice.controller;

import com.postoffice.model.Transaction;
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

public class TransactionsController {
    @FXML private TableView<Transaction> table;
    @FXML private ComboBox<String> filterCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField customerField;
    @FXML private TextField amountField;
    @FXML private Label limitLabel;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();
    private final ObservableList<Transaction> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        typeCombo.getItems().addAll("Stamp Sale", "Parcel Posting", "Registered Mail",
                "Express Mail", "Money Order");
        typeCombo.getSelectionModel().selectFirst();

        filterCombo.getItems().addAll("All", "Today", "Pending approval", "Approved", "Rejected");
        filterCombo.getSelectionModel().selectFirst();

        table.setItems(shown);
        refresh();
    }

    public void refresh() {
        applyFilter();
        table.refresh();
        if (Session.isPostMaster()) {
            limitLabel.setText("You can approve any transaction.");
        } else {
            limitLabel.setText("You can approve routine transactions up to " + Ui.money(store.routineLimit())
                    + " BDT. Larger ones need the Post Master.");
        }
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    private void applyFilter() {
        String f = filterCombo.getValue();
        if (f == null) f = "All";
        Transaction selectedBefore = table.getSelectionModel().getSelectedItem();
        shown.clear();
        for (Transaction t : store.getTransactions()) {
            boolean show = true;
            if (f.equals("Today")) show = t.getDate().equals(Dates.today());
            else if (f.equals("Pending approval")) show = Transaction.PENDING.equals(t.getStatus());
            else if (f.equals("Approved")) show = Transaction.APPROVED.equals(t.getStatus());
            else if (f.equals("Rejected")) show = Transaction.REJECTED.equals(t.getStatus());
            if (show) shown.add(t);
        }
        if (selectedBefore != null && shown.contains(selectedBefore)) {
            table.getSelectionModel().select(selectedBefore);
        }
    }

    @FXML
    private void handleRecord() {
        String customer = customerField.getText().trim();
        Double amount = Ui.parseAmount(amountField.getText());
        if (customer.isEmpty() || amount == null) {
            statusLabel.setText("Enter the customer and a valid amount.");
            return;
        }
        Transaction t = new Transaction(store.nextId("TX"), Dates.today(), typeCombo.getValue(),
                customer, amount, Session.username());
        store.getTransactions().add(0, t);
        Session.log("Recorded transaction " + t.getId() + " (" + t.getType() + ", " + t.getAmountText() + ")");
        statusLabel.setText("Recorded " + t.getId() + " - waiting for approval.");
        customerField.clear();
        amountField.clear();
        refresh();
    }

    @FXML
    private void handleApprove() {
        Transaction t = pendingSelection();
        if (t == null) return;
        t.approve(Session.username());
        store.addRevenue(t.getType(), t.getAmount(), "Transaction " + t.getId());
        Session.log("Approved transaction " + t.getId() + " (" + t.getAmountText() + ")");
        statusLabel.setText("Approved " + t.getId());
        refresh();
    }

    @FXML
    private void handleReject() {
        Transaction t = pendingSelection();
        if (t == null) return;
        t.reject(Session.username());
        Session.log("Rejected transaction " + t.getId());
        statusLabel.setText("Rejected " + t.getId());
        refresh();
    }

    private Transaction pendingSelection() {
        Transaction t = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(t)) return null;
        if (!Transaction.PENDING.equals(t.getStatus())) {
            Ui.warn(t.getId() + " is already " + t.getStatus() + ".");
            return null;
        }
        if (!Session.isPostMaster() && t.getAmount() > store.routineLimit()) {
            Ui.warn(t.getId() + " is above the routine limit (" + Ui.money(store.routineLimit())
                    + " BDT). Only the Post Master can decide it.");
            return null;
        }
        return t;
    }
}
