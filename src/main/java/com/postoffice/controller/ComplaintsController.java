package com.postoffice.controller;

import com.postoffice.model.Complaint;
import com.postoffice.service.DataStore;
import com.postoffice.service.Session;
import com.postoffice.util.Dates;
import com.postoffice.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ComplaintsController {
    @FXML private TableView<Complaint> table;
    @FXML private TextField customerField;
    @FXML private TextField subjectField;
    @FXML private TextArea detailsField;
    @FXML private TextArea detailArea;
    @FXML private Button resolveBtn;
    @FXML private Label statusLabel;

    private final DataStore store = DataStore.getInstance();

    @FXML
    public void initialize() {
        table.setItems(store.getComplaints());
        resolveBtn.setDisable(!Session.isPostMaster());
        showDetail();
    }

    public void refresh() {
        table.refresh();
        showDetail();
    }

    @FXML
    private void handleRowSelected() {
        showDetail();
    }

    private void showDetail() {
        Complaint c = table.getSelectionModel().getSelectedItem();
        if (c == null) {
            detailArea.setText("Select a complaint to read its details and notes.");
            return;
        }
        String notes = c.getNotes();
        if (notes.isEmpty()) notes = "(none)";
        detailArea.setText(c.getId() + " - " + c.getSubject() + "  (" + c.getStatus() + ")\n"
                + "Customer: " + c.getCustomer() + "   Date: " + c.getDate() + "\n\n"
                + c.getDetails() + "\n\nNotes:\n" + notes);
    }

    @FXML
    private void handleRegister() {
        String customer = customerField.getText().trim();
        String subject = subjectField.getText().trim();
        String details = detailsField.getText().trim();
        if (customer.isEmpty() || subject.isEmpty() || details.isEmpty()) {
            statusLabel.setText("Fill in customer, subject and details.");
            return;
        }
        Complaint c = new Complaint(store.nextId("CP"), Dates.today(), customer, subject, details);
        store.getComplaints().add(0, c);
        Session.log("Registered complaint " + c.getId() + " (" + subject + ")");
        statusLabel.setText("Registered " + c.getId());
        customerField.clear();
        subjectField.clear();
        detailsField.clear();
        refresh();
    }

    @FXML
    private void handleInReview() {
        Complaint c = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(c)) return;
        if (!Complaint.OPEN.equals(c.getStatus())) {
            Ui.warn(c.getId() + " is already " + c.getStatus() + ".");
            return;
        }
        c.setStatus(Complaint.IN_REVIEW);
        Session.log("Started reviewing complaint " + c.getId());
        refresh();
    }

    @FXML
    private void handleAddNote() {
        Complaint c = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(c)) return;
        String note = Ui.ask("Review note", "Note for " + c.getId() + ":", "");
        if (note == null) return;
        c.addNote(Session.username(), note);
        Session.log("Added note to complaint " + c.getId());
        refresh();
    }

    @FXML
    private void handleResolve() {
        Complaint c = table.getSelectionModel().getSelectedItem();
        if (Ui.noSelection(c)) return;
        if (Complaint.RESOLVED.equals(c.getStatus())) {
            Ui.warn(c.getId() + " is already resolved.");
            return;
        }
        String text = Ui.ask("Resolve complaint", "How was " + c.getId() + " resolved?", "");
        if (text == null) return;
        c.addNote(Session.username(), "RESOLVED: " + text);
        c.setStatus(Complaint.RESOLVED);
        Session.log("Resolved complaint " + c.getId());
        refresh();
    }
}
