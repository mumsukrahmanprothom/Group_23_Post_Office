package com.example.group_23_post_office;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class PostalRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private int recordID;
    private String recordType;
    private LocalDate entryDate, lastUpdated;
    private boolean verified;
    private boolean archived;
    private String status;
    private LocalDate archivedDate;

    public PostalRecord(int recordID, String recordType, String status) {
        this.recordID = recordID;
        this.recordType = recordType;
        this.status = status;
        entryDate = LocalDate.now();
        lastUpdated = entryDate;
    }
    public int getRecordID() { return recordID; }
    public String getRecordType() { return recordType; }
    public LocalDate getEntryDate() { return entryDate; }
    public LocalDate getLastUpdated() { return lastUpdated; }
    public boolean isVerified() { return verified; }
    public boolean isArchived() { return archived; }
    public String getStatus() { return status; }
    public LocalDate getArchivedDate() { return archivedDate; }
    public void setStatus(String status) { this.status = status; }
    public void editRecord() { lastUpdated = LocalDate.now(); verified = false; }
    public void verifyRecord() { verified = true; lastUpdated = LocalDate.now(); }
    public void setArchived(boolean archived) {
        this.archived = archived;
        archivedDate = archived ? LocalDate.now() : null;
        lastUpdated = LocalDate.now();
    }
    public void keepEntryDate(LocalDate date) { entryDate = date; }
    public abstract String getSummary();
    public abstract String getDetails();
    public String toString() {
        return recordType + " #" + recordID + " | " + getDetails() + " | " + status
                + " | entered " + entryDate + " | updated " + lastUpdated
                + " | verified: " + verified + " | archived: " + archived;
    }
}
