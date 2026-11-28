package com.example.group_23_post_office;

import java.time.LocalDate;
public class DataVerification {
    private int verificationID;
    private LocalDate verificationDate;
    private String status, notes;
    public DataVerification(int verificationID) {
        this.verificationID = verificationID; verificationDate = LocalDate.now();
        status = "Pending"; notes = "";
    }
    public int getVerificationID() { return verificationID; }
    public LocalDate getVerificationDate() { return verificationDate; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public boolean checkRequiredFields(String value) { return value != null && !value.trim().isEmpty(); }
    public boolean positive(double value) { return value > 0 && !Double.isInfinite(value); }
    public String validateDetails(PostalRecord record) {
        if (record.getRecordID() <= 0 || !checkRequiredFields(record.getStatus())) return "Invalid ID or status.";
        if (record instanceof Customer) {
            Customer c = (Customer) record;
            if (!checkRequiredFields(c.getName()) || !checkRequiredFields(c.getAddress()) || !checkRequiredFields(c.getContact())) return "Enter name, address and contact.";
            if (!c.getContact().matches("[+0-9 ()-]{7,25}") || c.getContact().replaceAll("[^0-9]", "").length() < 7) return "Enter a valid phone number (at least 7 digits).";
        } else if (record instanceof MailRecord) {
            MailRecord m = (MailRecord) record;
            if (!checkRequiredFields(m.getSenderInfo()) || !checkRequiredFields(m.getReceiverInfo()) || !checkRequiredFields(m.getMailType()) || !positive(m.getWeight()) || !checkRequiredFields(m.getDirection())) return "Enter sender, receiver, mail type, positive weight and direction.";
        } else if (record instanceof ParcelRecord) {
            ParcelRecord p = (ParcelRecord) record;
            if (!checkRequiredFields(p.getSenderInfo()) || !checkRequiredFields(p.getReceiverInfo()) || !checkRequiredFields(p.getParcelType()) || !positive(p.getWeight()) || !checkRequiredFields(p.getTrackingNumber())) return "Enter sender, receiver, parcel type and positive weight.";
        } else if (record instanceof TransactionRecord) {
            TransactionRecord t = (TransactionRecord) record;
            if (!checkRequiredFields(t.getDescription()) || !checkRequiredFields(t.getReference()) || !checkRequiredFields(t.getTransactionType()) || !positive(t.getAmount())) return "Enter description, reference, type and positive amount.";
        }
        return "";
    }
    public boolean markVerified(PostalRecord record) {
        notes = validateDetails(record);
        if (!notes.isEmpty()) { status = "Invalid"; return false; }
        record.verifyRecord(); status = "Verified"; return true;
    }
    public String toString() { return verificationID + " " + verificationDate + " " + status + " " + notes; }
}
