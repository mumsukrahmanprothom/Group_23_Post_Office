package com.postoffice.service;

import com.postoffice.model.ActivityEntry;
import com.postoffice.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ActivityLogger {
    private static final String LOG_FILE = "activity_log.txt";
    private static final ActivityLogger INSTANCE = new ActivityLogger();
    private final ObservableList<ActivityEntry> entries = FXCollections.observableArrayList();

    private ActivityLogger() {
        load();
    }

    public static ActivityLogger getInstance() { return INSTANCE; }

    public ObservableList<ActivityEntry> getEntries() { return entries; }

    public void log(User user, String action) {
        add(new ActivityEntry(user.getUsername(), user.getRole(), action));
    }

    public void logUnknown(String typedUsername, String action) {
        String name = typedUsername;
        if (name.trim().isEmpty()) name = "(blank)";
        add(new ActivityEntry(name, "-", action));
    }

    private void add(ActivityEntry entry) {
        entries.add(0, entry);
        append(entry);
    }

    private void append(ActivityEntry entry) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(entry.toFileLine());
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write activity log: " + e.getMessage());
        }
    }

    private void load() {
        File file = new File(LOG_FILE);
        if (!file.exists()) return;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] p = line.split(" \\| ", 4);
                if (p.length == 4) {
                    entries.add(0, new ActivityEntry(p[0], p[1], p[2], p[3]));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not read activity log: " + e.getMessage());
        }
    }
}
