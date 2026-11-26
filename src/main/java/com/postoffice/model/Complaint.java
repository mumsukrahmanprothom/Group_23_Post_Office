package com.postoffice.model;

public class Complaint {
    public static final String OPEN = "Open";
    public static final String IN_REVIEW = "In Review";
    public static final String RESOLVED = "Resolved";

    private final String id, date, customer, subject, details;
    private String status = OPEN;
    private final StringBuilder notes = new StringBuilder();

    public Complaint(String id, String date, String customer, String subject, String details) {
        this.id = id; this.date = date; this.customer = customer;
        this.subject = subject; this.details = details;
    }

    public void addNote(String by, String text) {
        notes.append("[").append(by).append("] ").append(text).append("\n");
    }

    public void setStatus(String status) { this.status = status; }

    public String getId()       { return id; }
    public String getDate()     { return date; }
    public String getCustomer() { return customer; }
    public String getSubject()  { return subject; }
    public String getDetails()  { return details; }
    public String getStatus()   { return status; }
    public String getNotes()    { return notes.toString(); }
}
