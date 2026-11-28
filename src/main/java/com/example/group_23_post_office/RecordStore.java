package com.example.group_23_post_office;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class RecordStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final File FILE = new File("post-office-data.bin");
    private ArrayList<PostalRecord> records = new ArrayList<>();
    private ArrayList<DailyTask> tasks = new ArrayList<>();
    private AccessPermission permission = new AccessPermission(1, 5, "Read and write", "Active");
    private ArrayList<String> backupHistory = new ArrayList<>();
    private int nextReportID = 1, nextBackupID = 1;
    public ArrayList<PostalRecord> getRecords() { return records; }
    public AccessPermission getPermission() { return permission; }
    public ArrayList<String> getBackupHistory() { return backupHistory; }
    public int nextReportID() { return nextReportID++; }
    public int nextBackupID() { return nextBackupID++; }
    public static RecordStore load() throws IOException, ClassNotFoundException {
        if (!FILE.exists()) return new RecordStore();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE))) {
            return (RecordStore) input.readObject();
        }
    }
    public void save() throws IOException {
        // Rewrite one complete snapshot so each file has only one stream header.
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE))) {
            output.writeObject(this);
        }
    }
    public PostalRecord find(int id) {
        for (PostalRecord record : records) if (record.getRecordID() == id) return record;
        return null;
    }
    public int nextID() {
        int max = 0;
        for (PostalRecord record : records) if (record.getRecordID() > max) max = record.getRecordID();
        return max + 1;
    }
    public void put(PostalRecord record) {
        PostalRecord old = find(record.getRecordID());
        if (old != null) records.remove(old);
        records.add(record);
    }
    public ArrayList<DailyTask> todayTasks() {
        ArrayList<DailyTask> today = new ArrayList<>();
        for (DailyTask task : tasks) if (task.getDate().equals(LocalDate.now())) today.add(task);
        if (today.isEmpty()) {
            today.add(new DailyTask("Customer", LocalDate.now()));
            today.add(new DailyTask("Mail", LocalDate.now()));
            today.add(new DailyTask("Parcel", LocalDate.now()));
            tasks.addAll(today);
        }
        return today;
    }
    public void completeTask(PostalRecord record) {
        for (DailyTask task : todayTasks()) {
            if (task.getCategory().equals(record.getRecordType()) && task.getRecordID() == 0) task.setRecordID(record.getRecordID());
        }
    }
    public ArrayList<PostalRecord> search(String category, String keyword, String archive, boolean keeper) {
        ArrayList<PostalRecord> results = new ArrayList<>();
        for (PostalRecord record : records) {
            if (!keeper && record.getRecordType().equals("Transaction")) continue;
            if (!category.equals("All") && !category.equals(record.getRecordType())) continue;
            if (archive.equals("Active") && record.isArchived()) continue;
            if (archive.equals("Archived") && !record.isArchived()) continue;
            if (!keyword.isEmpty()) {
                if (keyword.matches("[0-9]+")) {
                    if (!String.valueOf(record.getRecordID()).equals(keyword)) continue;
                } else if (!record.toString().toLowerCase().contains(keyword.toLowerCase())) continue;
            }
            results.add(record);
        }
        return results;
    }
}
