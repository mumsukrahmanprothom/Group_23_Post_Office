/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 7: Customer Complaints & Queries model. */
public class CustomerComplaintsQueriesModel implements Serializable {
    private String complaintId;
    private String customerName;
    private String complaintType;
    private String description;
    private String response;
    private String status;

    public CustomerComplaintsQueriesModel(String complaintId, String customerName,
                                          String complaintType, String description, String response,
                                          String status) {
        this.complaintId = complaintId;
        this.customerName = customerName;
        this.complaintType = complaintType;
        this.description = description;
        this.response = response;
        this.status = status;
    }

    public String getComplaintId() { return complaintId; }
    public void setComplaintId(String complaintId) { this.complaintId = complaintId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getComplaintType() { return complaintType; }
    public void setComplaintType(String complaintType) { this.complaintType = complaintType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Complaint " + complaintId + " (" + complaintType + ") - " + customerName;
    }
}