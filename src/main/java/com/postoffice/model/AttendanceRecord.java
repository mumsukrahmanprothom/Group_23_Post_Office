package com.postoffice.model;

public class AttendanceRecord {
    public static final String PRESENT = "Present";
    public static final String LATE = "Late";
    public static final String ABSENT = "Absent";

    private final String date, staff;
    private String status;
    private String recordedBy;

    public AttendanceRecord(String date, String staff, String status, String recordedBy) {
        this.date = date; this.staff = staff; this.status = status; this.recordedBy = recordedBy;
    }

    public void update(String status, String by) { this.status = status; this.recordedBy = by; }

    public String getDate()       { return date; }
    public String getStaff()      { return staff; }
    public String getStatus()     { return status; }
    public String getRecordedBy() { return recordedBy; }
}
