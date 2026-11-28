package com.example.group_23_post_office;

import java.time.LocalDate;
public class ArchiveRecord {
    private int archiveID;
    private LocalDate archivedDate;
    private String archiveLocation, recordType;
    public ArchiveRecord(PostalRecord record) {
        archiveID = record.getRecordID(); recordType = record.getRecordType();
        archivedDate = LocalDate.now(); archiveLocation = "Local record archive";
    }
    public int getArchiveID() { return archiveID; }
    public LocalDate getArchivedDate() { return archivedDate; }
    public String getArchiveLocation() { return archiveLocation; }
    public String getRecordType() { return recordType; }
    public boolean selectForArchive(PostalRecord record) {
        return !record.isArchived() && (record.getStatus().equals("Inactive") || record.getStatus().equals("Delivered") || record.getStatus().equals("Completed") || record.getEntryDate().isBefore(LocalDate.now().minusDays(30)));
    }
    public void archive(PostalRecord record) { record.setArchived(true); }
    public void retrieveArchived(PostalRecord record) { record.setArchived(false); }
    public String toString() { return archiveID + " " + recordType + " " + archiveLocation; }
}
