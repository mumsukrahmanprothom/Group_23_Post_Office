package com.example.group_23_post_office;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WorkspaceController {
    @FXML private Label roleLabel, titleLabel, messageLabel, formTitle, labelA, labelB, labelC, labelD, labelE, backupInfo;
    @FXML private Button goal1, goal2, goal3, goal4, goal5, goal6, goal7, goal8;
    @FXML private Button newButton, saveButton, reviewButton, verifyButton, archiveButton, restoreArchiveButton, openTaskButton;
    @FXML private VBox recordsPane, reportPane, accessPane, backupPane, tasksPane, formPane;
    @FXML private HBox rowD, rowE;
    @FXML private ComboBox<String> categoryBox, archiveBox, statusBox, directionBox, reportCategory, reportType, permissionLevel, permissionStatus, backupCategory, backupScope;
    @FXML private TextField searchField, idField, fieldA, fieldB, fieldC, fieldD, accountSearch;
    @FXML private TextArea detailsArea, reportArea, backupArea, permissionArea;
    @FXML private DatePicker fromDate, toDate;
    @FXML private ListView<DailyTask> taskList;
    @FXML private TableView<PostalRecord> recordTable, backupTable;
    @FXML private TableColumn<PostalRecord, Integer> idColumn, backupIdColumn;
    @FXML private TableColumn<PostalRecord, String> typeColumn, summaryColumn, statusColumn, backupTypeColumn, backupSummaryColumn;
    @FXML private TableColumn<PostalRecord, Boolean> verifiedColumn, archivedColumn;
    @FXML private TableColumn<PostalRecord, LocalDate> dateColumn;
    private RecordStore store;
    private boolean keeper;
    private int goal;
    private PostalRecord selected;
    private String formType;
    private RecordReport currentReport;

    @FXML private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, Integer>("recordID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, String>("recordType"));
        summaryColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, String>("summary"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, String>("status"));
        verifiedColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, Boolean>("verified"));
        archivedColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, Boolean>("archived"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, LocalDate>("entryDate"));
        backupIdColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, Integer>("recordID"));
        backupTypeColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, String>("recordType"));
        backupSummaryColumn.setCellValueFactory(new PropertyValueFactory<PostalRecord, String>("summary"));
        backupTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        archiveBox.getItems().addAll("Active", "Archived", "All");
        directionBox.getItems().addAll("Incoming", "Outgoing");
        reportType.getItems().addAll("All records", "Verified", "Unverified", "Archived");
        reportType.setValue("All records");
        permissionLevel.getItems().addAll("Read and write", "Read only", "No access");
        permissionStatus.getItems().addAll("Active", "Suspended");
        backupScope.getItems().addAll("Selected records", "All displayed records");
        backupScope.setValue("All displayed records");
    }
    public void setup(RecordStore store, boolean keeper) {
        this.store = store; this.keeper = keeper;
        roleLabel.setText(keeper ? "User 6 - Record Keeper" : "User 5 - Data Entry Operator / " + store.getPermission().getAccessLevel());
        if (keeper) {
            goal1.setText("1. Maintain records"); goal2.setText("2. Search / retrieve");
            goal3.setText("3. Update records"); goal4.setText("4. Archive records");
            goal5.setText("5. Verify records"); goal6.setText("6. Generate report");
            goal7.setText("7. Manage access"); goal8.setText("8. Backup / restore");
        } else {
            goal1.setText("1. Customer details"); goal2.setText("2. Register mail");
            goal3.setText("3. Register parcel"); goal4.setText("4. Edit records");
            goal5.setText("5. Verify data"); goal6.setText("6. Generate report");
            goal7.setText("7. Search records"); goal8.setText("8. Daily tasks");
        }
        categoryBox.getItems().addAll("All", "Customer", "Mail", "Parcel");
        if (keeper) categoryBox.getItems().add("Transaction");
        else reportType.getItems().remove("Archived");
        reportCategory.getItems().addAll(categoryBox.getItems());
        backupCategory.getItems().addAll(categoryBox.getItems());
        reportCategory.setValue("All"); backupCategory.setValue("All");
        fromDate.setValue(LocalDate.now().minusDays(30)); toDate.setValue(LocalDate.now());
        showGoal(1);
    }
    @FXML private void openGoal1() { showGoal(1); }
    @FXML private void openGoal2() { showGoal(2); }
    @FXML private void openGoal3() { showGoal(3); }
    @FXML private void openGoal4() { showGoal(4); }
    @FXML private void openGoal5() { showGoal(5); }
    @FXML private void openGoal6() { showGoal(6); }
    @FXML private void openGoal7() { showGoal(7); }
    @FXML private void openGoal8() { showGoal(8); }
    private void showPane(VBox pane, boolean show) { pane.setVisible(show); pane.setManaged(show); }
    private boolean canWrite() { return keeper || store.getPermission().canWrite(); }
    private boolean requireWrite() {
        if (!canWrite()) { message("Read-only access: changes are not permitted."); return false; }
        return true;
    }
    private void showGoal(int number) {
        goal = number; selected = null;
        message("");
        Button[] buttons = {goal1, goal2, goal3, goal4, goal5, goal6, goal7, goal8};
        titleLabel.setText(buttons[number - 1].getText());
        boolean report = number == 6;
        boolean access = keeper && number == 7;
        boolean backup = keeper && number == 8;
        boolean tasks = !keeper && number == 8;
        showPane(recordsPane, !report && !access && !backup && !tasks);
        showPane(reportPane, report); showPane(accessPane, access); showPane(backupPane, backup); showPane(tasksPane, tasks);
        if (report) { currentReport = null; reportArea.clear(); return; }
        if (access) { accountSearch.setText("user5"); lookupAccount(); return; }
        if (backup) { refreshBackup(); return; }
        if (tasks) { refreshTasks(); return; }
        boolean entry = !keeper && number <= 3;
        categoryBox.setDisable(entry);
        categoryBox.setValue(entry ? new String[]{"Customer", "Mail", "Parcel"}[number - 1] : "All");
        archiveBox.setValue(keeper && (number == 2 || number == 4) ? "All" : "Active");
        archiveBox.setDisable(!keeper || entry);
        searchField.clear();
        boolean edit = canWrite() && (entry || (!keeper && (number == 4 || number == 5)) || (keeper && (number == 1 || number == 3 || number == 5)));
        showPane(formPane, edit);
        newButton.setDisable(!canWrite() || !(entry || keeper && number == 1));
        saveButton.setDisable(!edit); reviewButton.setDisable(!edit);
        verifyButton.setDisable(!canWrite() || number != 5);
        archiveButton.setDisable(!keeper || number != 4);
        restoreArchiveButton.setDisable(!keeper || number != 4);
        refreshRecords();
        if (entry && edit) newRecord();
    }
    private void message(String value) { messageLabel.setText(value); }
    private boolean persist() {
        try { store.save(); return true; }
        catch (IOException e) {
            message("Could not save data: " + e.getMessage() + ". Reloading last saved data.");
            try { store = RecordStore.load(); }
            catch (Exception ignored) { message("Data file could not be reloaded. Close the app and check the file."); }
            return false;
        }
    }
    @FXML private void refreshRecords() {
        if (store == null) return;
        selected = null; formType = null; detailsArea.clear(); clearFields();
        recordTable.getItems().clear();
        for (PostalRecord r : store.search(categoryBox.getValue(), searchField.getText().trim(), archiveBox.getValue(), keeper)) {
            if (goal == 5 && r.isVerified()) continue;
            recordTable.getItems().add(r);
        }
        formTitle.setText("Select a record, or choose New record if available.");
    }
    private void clearFields() {
        idField.clear(); fieldA.clear(); fieldB.clear(); fieldC.clear(); fieldD.clear();
        statusBox.getItems().clear(); directionBox.setValue(null);
    }
    private void configureForm(String type) {
        formType = type;
        boolean customer = type.equals("Customer"), mail = type.equals("Mail"), transaction = type.equals("Transaction");
        labelA.setText(customer ? "Name" : transaction ? "Description" : "Sender");
        labelB.setText(customer ? "Address" : transaction ? "Reference" : "Receiver");
        labelC.setText(customer ? "Contact" : transaction ? "Transaction type" : mail ? "Mail type" : "Parcel type");
        labelD.setText(transaction ? "Amount" : "Weight (kg)"); labelE.setText("Direction");
        rowD.setVisible(!customer); rowD.setManaged(!customer);
        rowE.setVisible(mail); rowE.setManaged(mail);
        statusBox.getItems().clear();
        if (customer) statusBox.getItems().addAll("Active", "Inactive");
        else if (transaction) statusBox.getItems().addAll("Pending", "Completed", "Cancelled");
        else statusBox.getItems().addAll("Registered", "In transit", "Delivered", "Inactive");
        statusBox.getSelectionModel().selectFirst(); directionBox.setValue("Incoming");
    }
    @FXML private void newRecord() {
        if (!requireWrite()) return;
        if (!(!keeper && goal <= 3 || keeper && goal == 1)) return;
        String category = categoryBox.getValue();
        if (category.equals("All")) { message("Choose a specific record category and click New record."); return; }
        if (keeper && !category.equals("Transaction")) { message("Use User 5 to register customer, mail or parcel records. User 6 can edit existing records here."); return; }
        recordTable.getSelectionModel().clearSelection(); selected = null; clearFields(); configureForm(category);
        formTitle.setText("New " + category + " record"); idField.setText(String.valueOf(store.nextID()));
        idField.setEditable(category.equals("Customer"));
        detailsArea.clear(); message("Enter the details, then Review or Save record.");
    }
    @FXML private void selectRecord() {
        PostalRecord record = recordTable.getSelectionModel().getSelectedItem();
        if (record == null) { message("Select a row in the table first."); return; }
        selected = record; clearFields(); configureForm(record.getRecordType());
        idField.setText(String.valueOf(record.getRecordID())); idField.setEditable(false);
        statusBox.setValue(record.getStatus());
        if (record instanceof Customer) {
            Customer c = (Customer) record;
            fieldA.setText(c.getName()); fieldB.setText(c.getAddress()); fieldC.setText(c.getContact());
        } else if (record instanceof MailRecord) {
            MailRecord m = (MailRecord) record;
            fieldA.setText(m.getSenderInfo()); fieldB.setText(m.getReceiverInfo()); fieldC.setText(m.getMailType());
            fieldD.setText(String.valueOf(m.getWeight())); directionBox.setValue(m.getDirection());
        } else if (record instanceof ParcelRecord) {
            ParcelRecord p = (ParcelRecord) record;
            fieldA.setText(p.getSenderInfo()); fieldB.setText(p.getReceiverInfo()); fieldC.setText(p.getParcelType());
            fieldD.setText(String.valueOf(p.getWeight()));
        } else if (record instanceof TransactionRecord) {
            TransactionRecord t = (TransactionRecord) record;
            fieldA.setText(t.getDescription()); fieldB.setText(t.getReference()); fieldC.setText(t.getTransactionType());
            fieldD.setText(String.valueOf(t.getAmount()));
        }
        formTitle.setText("Selected " + record.getRecordType() + " #" + record.getRecordID());
        detailsArea.setText(record.toString()); message("Record loaded.");
    }
    private PostalRecord readForm() {
        if (formType == null) { message("Open a selected record or start a new one first."); return null; }
        try {
            int id = Integer.parseInt(idField.getText().trim());
            if (selected == null && store.find(id) != null) { message("This record ID is already used."); return null; }
            if (selected != null && selected.isArchived()) { message("Retrieve the archived record before editing."); return null; }
            String a = fieldA.getText().trim(), b = fieldB.getText().trim(), c = fieldC.getText().trim();
            PostalRecord record;
            if (formType.equals("Customer")) record = new Customer(id, a, b, c);
            else {
                double number = Double.parseDouble(fieldD.getText().trim());
                if (formType.equals("Mail")) record = new MailRecord(id, a, b, c, number, directionBox.getValue());
                else if (formType.equals("Parcel")) record = new ParcelRecord(id, a, b, c, number, ParcelRecord.generateTrackingNo(id));
                else record = new TransactionRecord(id, a, b, c, number);
            }
            record.setStatus(statusBox.getValue());
            if (selected != null) { record.keepEntryDate(selected.getEntryDate()); record.editRecord(); }
            String error = new DataVerification(id).validateDetails(record);
            if (!error.isEmpty()) { message(error); return null; }
            return record;
        } catch (NumberFormatException e) { message("Enter a whole-number ID and a valid numeric weight or amount."); return null; }
    }
    @FXML private void reviewRecord() {
        PostalRecord record = readForm();
        if (record != null) { detailsArea.setText(record.toString()); message("Required fields are valid. Review the details before saving."); }
    }
    @FXML private void saveRecord() {
        if (!requireWrite()) return;
        if (selected == null && !(!keeper && goal <= 3 || keeper && goal == 1 && "Transaction".equals(formType))) { message("Select an existing record first."); return; }
        PostalRecord record = readForm(); if (record == null) return;
        if (!confirm("Save this record?", record.toString())) return;
        boolean isNew = selected == null;
        store.put(record);
        if (!keeper && isNew) store.completeTask(record);
        if (persist()) { refreshRecords(); detailsArea.setText(record.toString()); message("Saved record #" + record.getRecordID() + (record instanceof ParcelRecord ? ". Tracking: " + ((ParcelRecord) record).getTrackingNumber() : "")); }
    }
    @FXML private void verifyRecord() {
        if (!requireWrite() || goal != 5) return;
        if (selected == null) { message("Select and open a record first."); return; }
        PostalRecord candidate = readForm(); if (candidate == null) return;
        DataVerification verification = new DataVerification(candidate.getRecordID());
        if (!confirm("Confirm verification?", candidate.toString())) return;
        if (!verification.markVerified(candidate)) { message(verification.getNotes()); return; }
        store.put(candidate);
        if (persist()) { refreshRecords(); detailsArea.setText(candidate.toString()); message("Record verified and saved."); }
    }
    @FXML private void archiveRecord() {
        if (!keeper || goal != 4) return;
        if (selected == null) { message("Select and open a record first."); return; }
        ArchiveRecord archive = new ArchiveRecord(selected);
        if (!archive.selectForArchive(selected)) { message("Archive only inactive, delivered, completed, or records older than 30 days."); return; }
        if (!confirm("Archive record?", selected.toString())) return;
        archive.archive(selected);
        if (persist()) { refreshRecords(); message("Record archived. Filter Archived to view it."); }
    }
    @FXML private void retrieveArchive() {
        if (!keeper || goal != 4) return;
        if (selected == null || !selected.isArchived()) { message("Open an archived record first."); return; }
        if (!confirm("Return to active records?", selected.toString())) return;
        new ArchiveRecord(selected).retrieveArchived(selected);
        if (persist()) { refreshRecords(); message("Archived record retrieved."); }
    }
    @FXML private void generateReport() {
        LocalDate from = fromDate.getValue(), to = toDate.getValue();
        if (from == null || to == null || from.isAfter(to)) { message("Choose a valid start and end date."); return; }
        currentReport = new RecordReport(store.nextReportID(), reportType.getValue(), from, to);
        ArrayList<PostalRecord> allowed = store.search("All", "", keeper ? "All" : "Active", keeper);
        reportArea.setText(currentReport.generateReport(allowed, reportCategory.getValue(), from, to));
        if (persist()) message("Report generated. Use Download report to save a text file.");
    }
    @FXML private void downloadReport() {
        if (currentReport == null) { message("Generate a report first."); return; }
        FileChooser chooser = new FileChooser(); chooser.setTitle("Save report"); chooser.setInitialFileName("report-" + currentReport.getReportID() + ".txt");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File file = chooser.showSaveDialog(titleLabel.getScene().getWindow()); if (file == null) return;
        if (isDataFile(file)) { message("Choose a text report file, not the main data file."); return; }
        try { currentReport.downloadReport(file); message("Report saved to " + file.getAbsolutePath()); }
        catch (IOException e) { message("Report could not be saved: " + e.getMessage()); }
    }
    private void refreshTasks() {
        taskList.getItems().setAll(store.todayTasks()); openTaskButton.setDisable(!canWrite());
    }
    @FXML private void openTask() {
        if (!requireWrite()) return;
        DailyTask task = taskList.getSelectionModel().getSelectedItem();
        if (task == null || task.getRecordID() != 0) { message("Select a pending task."); return; }
        if (task.getCategory().equals("Customer")) showGoal(1);
        else if (task.getCategory().equals("Mail")) showGoal(2);
        else showGoal(3);
        message("Complete this entry, save, then return to Daily tasks to see its status.");
    }
    @FXML private void lookupAccount() {
        if (!keeper) return;
        String query = accountSearch.getText().trim();
        if (!query.equalsIgnoreCase("user5") && !query.equals("5")) {
            permissionArea.setText("Only user5 (ID 5) is managed by this module. User6 is the administrator.");
            permissionLevel.setValue(null); permissionStatus.setValue(null); return;
        }
        AccessPermission p = store.getPermission();
        permissionLevel.setValue(p.getAccessLevel()); permissionStatus.setValue(p.getStatus()); permissionArea.setText(p.toString());
    }
    @FXML private void savePermission() {
        if (!keeper) return;
        String query = accountSearch.getText().trim();
        if ((!query.equalsIgnoreCase("user5") && !query.equals("5")) || permissionLevel.getValue() == null || permissionStatus.getValue() == null) { message("Search for user5 first and choose both settings."); return; }
        if (!confirm("Save access changes?", permissionLevel.getValue() + " / " + permissionStatus.getValue())) return;
        store.getPermission().setAccessLevel(permissionLevel.getValue()); store.getPermission().setStatus(permissionStatus.getValue());
        if (persist()) { lookupAccount(); message("Permission saved. It will apply at User 5's next login."); }
    }
    @FXML private void refreshBackup() {
        if (store == null || !keeper) return;
        backupTable.getItems().setAll(store.search(backupCategory.getValue(), "", "All", true));
        backupArea.clear();
        for (String line : store.getBackupHistory()) backupArea.appendText(line + "\n");
        backupInfo.setText(backupTable.getItems().size() + " available records. Select rows or use all displayed records.");
    }
    @FXML private void previewBackup() {
        ArrayList<PostalRecord> records = backupSelection();
        try {
            RecordBackup preview = new RecordBackup(0, backupScope.getValue(), "Preview", records);
            backupInfo.setText(records.size() + " records selected. Approximate backup size: " + preview.estimateSize() + " bytes.");
        } catch (IOException e) { message("Cannot estimate backup size: " + e.getMessage()); }
    }
    private ArrayList<PostalRecord> backupSelection() {
        ArrayList<PostalRecord> records = new ArrayList<>();
        if (backupScope.getValue().equals("Selected records")) records.addAll(backupTable.getSelectionModel().getSelectedItems());
        else records.addAll(backupTable.getItems());
        return records;
    }
    @FXML private void createBackup() {
        if (!keeper) return;
        ArrayList<PostalRecord> records = backupSelection();
        if (records.isEmpty()) { message("Select records to back up."); return; }
        FileChooser chooser = backupChooser("Save binary backup"); chooser.setInitialFileName("postal-backup-" + LocalDate.now() + ".bin");
        File file = chooser.showSaveDialog(titleLabel.getScene().getWindow()); if (file == null) return;
        if (isDataFile(file)) { message("Choose a separate backup file, not the main data file."); return; }
        RecordBackup backup = new RecordBackup(store.nextBackupID(), backupScope.getValue(), file.getAbsolutePath(), records);
        try {
            backup.createBackup(file); store.getBackupHistory().add(backup.toString() + " | " + file.length() + " bytes");
            if (persist()) { refreshBackup(); backupInfo.setText("Completed: " + records.size() + " records, " + file.length() + " bytes."); message("Backup saved successfully."); }
        } catch (IOException e) { message("Backup failed: " + e.getMessage()); }
    }
    @FXML private void restoreBackup() {
        if (!keeper) return;
        File file = backupChooser("Open a trusted backup file").showOpenDialog(titleLabel.getScene().getWindow()); if (file == null) return;
        if (isDataFile(file)) { message("Choose a backup file, not the main data file."); return; }
        try {
            RecordBackup backup = RecordBackup.restoreRecords(file);
            for (PostalRecord record : backup.getRecords()) {
                String error = new DataVerification(record.getRecordID()).validateDetails(record);
                if (!error.isEmpty()) { message("Invalid backup record: " + error); return; }
                PostalRecord current = store.find(record.getRecordID());
                if (current != null && !current.getRecordType().equals(record.getRecordType())) { message("Backup contains an ID used by another record type. Nothing restored."); return; }
            }
            if (!confirm("Restore backup?", "Restore " + backup.getRecords().size() + " records? Matching IDs will be replaced. Other records will remain.")) return;
            for (PostalRecord record : backup.getRecords()) store.put(record);
            store.getBackupHistory().add("Restored " + backup.getRecords().size() + " records from " + file.getAbsolutePath());
            if (persist()) { refreshBackup(); message("Backup restored successfully."); }
        } catch (Exception e) { message("Restore failed: " + e.getMessage()); }
    }
    private boolean isDataFile(File file) {
        try { return file.getCanonicalFile().equals(new File("post-office-data.bin").getCanonicalFile()); }
        catch (IOException e) { return true; }
    }
    private FileChooser backupChooser(String title) {
        FileChooser chooser = new FileChooser(); chooser.setTitle(title);
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Binary backup", "*.bin")); return chooser;
    }
    private boolean confirm(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText(title); alert.initOwner(titleLabel.getScene().getWindow());
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
    @FXML private void logout() {
        try {
            Stage stage = (Stage) roleLabel.getScene().getWindow();
            stage.setMinWidth(0); stage.setMinHeight(0);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("login.fxml")), 520, 340));
            stage.sizeToScene();
        } catch (IOException e) { message("Could not return to login: " + e.getMessage()); }
    }
}
