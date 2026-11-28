
package com.example.group_23_post_office;

public class Complaint {

    private String complaintId;
    private String complaintDetails;
    private String status;
    private String resolutionNote;

    public Complaint(String complaintId, String complaintDetails,
                     String status, String resolutionNote) {

        this.complaintId = complaintId;
        this.complaintDetails = complaintDetails;
        this.status = status;
        this.resolutionNote = resolutionNote;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolutionNote() {
        return resolutionNote;
    }

    public void setResolutionNote(String resolutionNote) {
        this.resolutionNote = resolutionNote;
    }
}
