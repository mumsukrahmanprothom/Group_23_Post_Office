package com.example.group_23_post_office;

import java.io.Serializable;
import java.time.LocalDate;
public class DailyTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private String category;
    private LocalDate date;
    private int recordID;
    public DailyTask(String category, LocalDate date) { this.category = category; this.date = date; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public int getRecordID() { return recordID; }
    public void setRecordID(int recordID) { this.recordID = recordID; }
    public String getStatus() { return recordID == 0 ? "Pending" : "Completed"; }
    public String toString() { return category + " entry - " + getStatus() + (recordID == 0 ? "" : " (#" + recordID + ")"); }
}
