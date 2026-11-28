package com.example.group_23_post_office;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataStore {

    private static final DataStore INSTANCE = new DataStore();

    private ObservableList<MailRecord> mailRecords;

    private ArrayList<Customer> customerRecords;
    private ArrayList<CustomerRequest> requestRecords;
    private ArrayList<Complaint> complaintRecords;
    private ArrayList<String> feedbackRecords;

    private int nextId = 1001;

    private DataStore() {
        mailRecords = FXCollections.observableArrayList();

        customerRecords = new ArrayList<Customer>();
        requestRecords = new ArrayList<CustomerRequest>();
        complaintRecords = new ArrayList<Complaint>();
        feedbackRecords = new ArrayList<String>();
    }

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public ObservableList<MailRecord> getMailRecords() {
        return mailRecords;
    }

    public void addMail(MailRecord mail) {
        mailRecords.add(mail);
    }

    public MailRecord findMail(String trackingId) {
        for (MailRecord mail : mailRecords) {
            if (mail.getTrackingId().equalsIgnoreCase(trackingId)) {
                return mail;
            }
        }
        return null;
    }

    public String generateTrackingId() {
        String trackingId = "PO" + nextId;
        nextId++;
        return trackingId;
    }

    public void removeMail(MailRecord mail) {
        mailRecords.remove(mail);
    }

    public ArrayList<Customer> getCustomerRecords() {
        return customerRecords;
    }

    public ArrayList<CustomerRequest> getRequestRecords() {
        return requestRecords;
    }

    public ArrayList<Complaint> getComplaintRecords() {
        return complaintRecords;
    }

    public ArrayList<String> getFeedbackRecords() {
        return feedbackRecords;
    }
}
