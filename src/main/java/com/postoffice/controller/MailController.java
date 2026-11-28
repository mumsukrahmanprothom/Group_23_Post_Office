package com.postoffice.controller;

import com.postoffice.model.MailItem;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MailController {
    @FXML private TableView<MailItem> table;
    @FXML private ComboBox<String> viewCombo;
    @FXML private ComboBox<String> categoryCombo;
    @FXML private TextField senderField;
    @FXML private TextField recipientField;
    @FXML private TextField destinationField;
    @FXML private TextField trackField;
    @FXML private TextArea historyArea;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();
    private final ObservableList<MailItem> shown = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        categoryCombo.getItems().addAll(MailItem.MAIL, MailItem.PARCEL);
        categoryCombo.getSelectionModel().selectFirst();

        viewCombo.getItems().addAll("All items", "Mail only", "Parcels only", "In progress",
                "Delivered", "Delivered - not verified");
        viewCombo.getSelectionModel().selectFirst();

        table.setItems(shown);
        refresh();
    }

    public void refresh() {
        applyFilter();
        table.refresh();
        showHistory();
    }

    @FXML
    private void handleFilter() {
        applyFilter();
    }

    @FXML
    private void handleRowSelected() {
        showHistory();
    }

    private void applyFilter() {
        String v = viewCombo.getValue();
        if (v == null) v = "All items";
        MailItem selectedBefore = table.getSelectionModel().getSelectedItem();
        shown.clear();
        for (MailItem m : store.getMail()) {
            boolean show = true;
            if (v.equals("Mail only")) show = MailItem.MAIL.equals(m.getCategory());
            else if (v.equals("Parcels only")) show = MailItem.PARCEL.equals(m.getCategory());
            else if (v.equals("In progress")) show = m.isInProgress();
            else if (v.equals("Delivered")) show = MailItem.DELIVERED.equals(m.getStatus());
            else if (v.equals("Delivered - not verified")) show = MailItem.DELIVERED.equals(m.getStatus()) && !m.isVerified();
            if (show) shown.add(m);
        }
        if (selectedBefore != null && shown.contains(selectedBefore)) {
            table.getSelectionModel().select(selectedBefore);
        }
    }

    private void showHistory() {
        MailItem m = table.getSelectionModel().getSelectedItem();
        if (m == null) {
            historyArea.setText("Select an item (or enter a tracking number) to see its tracking history.");
        } else {
            historyArea.setText("Tracking " + m.getId() + "  (" + m.getSender() + " -> " + m.getRecipient()
                    + ", " + m.getDestination() + ")\n\n" + m.getHistoryText());
        }
    }

    @FXML
    private void handleRegister() {
        String sender = senderField.getText().trim();
        String recipient = recipientField.getText().trim();
        String destination = destinationField.getText().trim();
        if (sender.isEmpty() || recipient.isEmpty() || destination.isEmpty()) {
            statusLabel.setText("Fill in sender, recipient and destination.");
            return;
        }
        MailItem m = new MailItem(store.nextId("PO"), categoryCombo.getValue(), sender, recipient,
                destination, Session.username());
        store.getMail().add(0, m);
        Session.log("Registered " + m.getCategory().toLowerCase() + " " + m.getId());
        statusLabel.setText("Registered " + m.getId());
        senderField.clear();
        recipientField.clear();
        destinationField.clear();
        refresh();
    }

    @FXML
    private void handleAdvance() {
        MailItem m = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(m)) return;
        if (!m.advance(Session.username())) {
            Ui.warn(m.getId() + " is already " + m.getStatus() + ".");
            return;
        }
        Session.log("Moved " + m.getId() + " to '" + m.getStatus() + "'");
        statusLabel.setText(m.getId() + " is now " + m.getStatus());
        refresh();
    }

    @FXML
    private void handleReturn() {
        MailItem m = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(m)) return;
        if (!m.markReturned(Session.username())) {
            Ui.warn(m.getId() + " is already " + m.getStatus() + " and cannot be returned.");
            return;
        }
        Session.log("Marked " + m.getId() + " as returned to sender");
        refresh();
    }

    @FXML
    private void handleVerify() {
        MailItem m = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(m)) return;
        if (!m.verifyDelivery(Session.username())) {
            Ui.warn("Only delivered items that are not yet verified can be verified.");
            return;
        }
        Session.log("Verified delivery record of " + m.getId());
        statusLabel.setText("Delivery of " + m.getId() + " verified.");
        refresh();
    }

    @FXML
    private void handleTrack() {
        String id = trackField.getText().trim();
        if (id.isEmpty()) return;
        for (MailItem m : store.getMail()) {
            if (m.getId().equalsIgnoreCase(id)) {
                viewCombo.getSelectionModel().selectFirst();
                applyFilter();
                table.getSelectionModel().select(m);
                table.scrollTo(m);
                showHistory();
                Session.log("Reviewed tracking of " + m.getId());
                statusLabel.setText("");
                return;
            }
        }
        statusLabel.setText("No item with tracking number " + id);
    }
}
