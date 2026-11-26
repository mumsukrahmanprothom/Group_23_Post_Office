package com.postoffice.model;

public class Duty {
    public static final String ASSIGNED = "Assigned";
    public static final String IN_PROGRESS = "In Progress";
    public static final String DONE = "Done";

    private final String id, staff, task, date, assignedBy;
    private String status = ASSIGNED;

    public Duty(String id, String staff, String task, String date, String assignedBy) {
        this.id = id; this.staff = staff; this.task = task; this.date = date; this.assignedBy = assignedBy;
    }

    public void setStatus(String status) { this.status = status; }

    public String getId()         { return id; }
    public String getStaff()      { return staff; }
    public String getTask()       { return task; }
    public String getDate()       { return date; }
    public String getAssignedBy() { return assignedBy; }
    public String getStatus()     { return status; }
}
