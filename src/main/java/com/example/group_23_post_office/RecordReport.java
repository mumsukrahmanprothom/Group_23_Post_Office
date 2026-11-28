package com.example.group_23_post_office;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
public class RecordReport {
    private int reportID;
    private String reportType, dateRange;
    private LocalDate generatedDate;
    private String content;
    public RecordReport(int reportID, String reportType, LocalDate from, LocalDate to) {
        this.reportID = reportID; this.reportType = reportType;
        dateRange = from + " to " + to; generatedDate = LocalDate.now();
    }
    public int getReportID() { return reportID; }
    public String getReportType() { return reportType; }
    public String getDateRange() { return dateRange; }
    public LocalDate getGeneratedDate() { return generatedDate; }
    public String generateReport(ArrayList<PostalRecord> records, String category, LocalDate from, LocalDate to) {
        int count = 0;
        String rows = "";
        for (PostalRecord r : records) {
            if (!category.equals("All") && !category.equals(r.getRecordType())) continue;
            if (r.getEntryDate().isBefore(from) || r.getEntryDate().isAfter(to)) continue;
            if (reportType.equals("Verified") && !r.isVerified()) continue;
            if (reportType.equals("Unverified") && r.isVerified()) continue;
            if (reportType.equals("Archived") && !r.isArchived()) continue;
            rows += r.toString() + "\n"; count++;
        }
        content = "POST OFFICE RECORD REPORT\nReport #" + reportID + " | " + generatedDate + "\n" + reportType + " / " + category + "\nDate range: " + dateRange + "\nTotal records: " + count + "\n\n" + rows;
        return content;
    }
    public void downloadReport(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content); writer.close();
    }
    public String toString() { return content; }
}
