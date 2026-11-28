package com.postoffice.controller;

import com.postoffice.model.Refund;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Dates;
import com.postoffice.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RefundsController {
    @FXML private TableView<Refund> table;
    @FXML private TextField customerField;
    @FXML private TextField amountField;
    @FXML private TextField reasonField;
    @FXML private Button approveBtn;
    @FXML private Button rejectBtn;
    @FXML private Label noteLabel;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();

    @FXML
    public void initialize() {
        table.setItems(store.getRefunds());
        boolean pm = Session.isPostMaster();
        approveBtn.setDisable(!pm);
        rejectBtn.setDisable(!pm);
        if (pm) {
            noteLabel.setText("Verified requests are ready for your decision.");
        } else {
            noteLabel.setText("You verify requests; the Post Master approves or rejects them.");
        }
    }

    public void refresh() {
        table.refresh();
    }

    @FXML
    private void handleSubmit() {
        String customer = customerField.getText().trim();
        String reason = reasonField.getText().trim();
        Double amount = Ui.parseAmount(amountField.getText());
        if (customer.isEmpty() || reason.isEmpty() || amount == null) {
            statusLabel.setText("Enter customer, a valid amount and a reason.");
            return;
        }
        Refund r = new Refund(store.nextId("RF"), Dates.today(), customer, amount, reason);
        store.getRefunds().add(0, r);
        Session.log("Submitted refund request " + r.getId() + " (" + r.getAmountText() + ")");
        statusLabel.setText("Submitted " + r.getId());
        customerField.clear();
        amountField.clear();
        reasonField.clear();
        refresh();
    }

    @FXML
    private void handleVerify() {
        Refund r = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(r)) return;
        if (!Refund.PENDING.equals(r.getStatus())) {
            Ui.warn(r.getId() + " is " + r.getStatus() + " - only pending requests can be verified.");
            return;
        }
        r.verify(Session.username());
        Session.log("Verified refund request " + r.getId());
        refresh();
    }

    @FXML
    private void handleApprove() {
        Refund r = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(r)) return;
        if (!r.isOpen()) {
            Ui.warn(r.getId() + " is already " + r.getStatus() + ".");
            return;
        }
        r.approve(Session.username());
        store.addExpense("Refund", r.getAmount(), "Refund " + r.getId() + " to " + r.getCustomer());
        Session.log("Approved refund " + r.getId() + " (" + r.getAmountText() + ")");
        refresh();
    }

    @FXML
    private void handleReject() {
        Refund r = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(r)) return;
        if (!r.isOpen()) {
            Ui.warn(r.getId() + " is already " + r.getStatus() + ".");
            return;
        }
        r.reject(Session.username());
        Session.log("Rejected refund " + r.getId());
        refresh();
    }
}
