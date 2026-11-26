package com.example.group_23_post_office;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class RecordBackup implements Serializable {
    private static final long serialVersionUID = 1L;
    private int backupID;
    private LocalDate backupDate;
    private String backupType, location, status;
    private ArrayList<PostalRecord> records;
    public RecordBackup(int backupID, String backupType, String location, ArrayList<PostalRecord> records) {
        this.backupID = backupID; this.backupType = backupType; this.location = location;
        this.records = records; backupDate = LocalDate.now(); status = "Pending";
    }
    public int getBackupID() { return backupID; }
    public LocalDate getBackupDate() { return backupDate; }
    public String getBackupType() { return backupType; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public ArrayList<PostalRecord> getRecords() { return records; }
    public int estimateSize() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(this);
        }
        return bytes.size();
    }
    public void createBackup(File file) throws IOException {
        status = "Completed";
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(this);
        } catch (IOException e) { status = "Failed"; throw e; }
    }
    public static RecordBackup restoreRecords(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (RecordBackup) input.readObject();
        }
    }
    public String toString() { return backupDate + " | " + backupType + " | " + records.size() + " records | " + status + " | " + location; }
}
