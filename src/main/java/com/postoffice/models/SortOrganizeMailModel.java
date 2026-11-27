/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 3: Sort and Organize Received Mail model. */
public class SortOrganizeMailModel implements Serializable {
    private String mailId;
    private String category;
    private String priority;
    private String destinationPostOffice;
    private String processedBy;
    private String status;

    public SortOrganizeMailModel(String mailId, String category, String priority,
                                 String destinationPostOffice, String processedBy, String status) {
        this.mailId = mailId;
        this.category = category;
        this.priority = priority;
        this.destinationPostOffice = destinationPostOffice;
        this.processedBy = processedBy;
        this.status = status;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getDestinationPostOffice() { return destinationPostOffice; }
    public void setDestinationPostOffice(String destinationPostOffice) { this.destinationPostOffice = destinationPostOffice; }
    public String getProcessedBy() { return processedBy; }
    public void setProcessedBy(String processedBy) { this.processedBy = processedBy; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Mail " + mailId + " | Category: " + category + " | Priority: " + priority;
    }
}