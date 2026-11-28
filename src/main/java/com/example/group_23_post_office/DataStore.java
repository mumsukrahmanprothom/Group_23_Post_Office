package com.example.group_23_post_office;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataStore {

    private static final DataStore INSTANCE = new DataStore();

    private ObservableList<MROMailRecord> mailRecords;

    private ArrayList<CSOCustomer> customerRecords;
    private ArrayList<CustomerRequest> requestRecords;
    private ArrayList<Complaint> complaintRecords;
    private ArrayList<String> feedbackRecords;

    private int nextId = 1001;

    private DataStore() {
        mailRecords = FXCollections.observableArrayList();

        customerRecords = new ArrayList<CSOCustomer>();
        requestRecords = new ArrayList<CustomerRequest>();
        complaintRecords = new ArrayList<Complaint>();
        feedbackRecords = new ArrayList<String>();
    }

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public ObservableList<MROMailRecord> getMailRecords() {
        return mailRecords;
    }

    public void addMail(MROMailRecord mail) {
        mailRecords.add(mail);
    }

    public MROMailRecord findMail(String trackingId) {
        for (MROMailRecord mail : mailRecords) {
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

    public void removeMail(MROMailRecord mail) {
        mailRecords.remove(mail);
    }

    public ArrayList<CSOCustomer> getCustomerRecords() {
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
