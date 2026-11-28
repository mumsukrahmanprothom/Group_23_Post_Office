package com.postoffice.model;

import com.postoffice.util.Dates;

import java.util.ArrayList;
import java.util.List;

public class MailItem {
    public static final String MAIL = "Mail";
    public static final String PARCEL = "Parcel";

    public static final String RECEIVED = "Received";
    public static final String SORTED = "Sorted";
    public static final String OUT = "Out for Delivery";
    public static final String DELIVERED = "Delivered";
    public static final String RETURNED = "Returned";

    private static final String[] FLOW = {RECEIVED, SORTED, OUT, DELIVERED};

    private final String id, category, sender, recipient, destination;
    private String status = RECEIVED;
    private boolean verified;
    private String lastUpdated;
    private final List<String> history = new ArrayList<>();

    public MailItem(String id, String category, String sender, String recipient,
                    String destination, String registeredBy) {
        this.id = id; this.category = category; this.sender = sender;
        this.recipient = recipient; this.destination = destination;
        addEvent("Received at counter (registered by " + registeredBy + ")");
    }

    private void addEvent(String text) {
        lastUpdated = Dates.now();
        history.add(lastUpdated + "  -  " + text);
    }

    public boolean advance(String by) {
        int i = -1;
        for (int k = 0; k < FLOW.length; k++) {
            if (FLOW[k].equals(status)) i = k;
        }
        if (i < 0 || i >= FLOW.length - 1) return false;
        status = FLOW[i + 1];
        addEvent(status + " (by " + by + ")");
        return true;
    }

    public boolean markReturned(String by) {
        if (DELIVERED.equals(status) || RETURNED.equals(status)) return false;
        status = RETURNED;
        addEvent("Returned to sender (by " + by + ")");
        return true;
    }

    public boolean verifyDelivery(String by) {
        if (!DELIVERED.equals(status) || verified) return false;
        verified = true;
        addEvent("Delivery record verified (by " + by + ")");
        return true;
    }

    public boolean isInProgress() { return !DELIVERED.equals(status) && !RETURNED.equals(status); }

    public String getId()          { return id; }
    public String getCategory()    { return category; }
    public String getSender()      { return sender; }
    public String getRecipient()   { return recipient; }
    public String getDestination() { return destination; }
    public String getStatus()      { return status; }
    public boolean isVerified()    { return verified; }
    public String getVerifiedText(){ return DELIVERED.equals(status) ? (verified ? "Yes" : "No") : "-"; }
    public String getLastUpdated() { return lastUpdated; }
    public String getHistoryText() {
        String text = "";
        for (String line : history) text = text + line + "\n";
        return text;
    }
}
