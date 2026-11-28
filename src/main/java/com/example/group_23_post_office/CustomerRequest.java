
package com.example.group_23_post_office;

public class CustomerRequest {

    private String requestId;
    private String requestDetails;
    private String requestDate;
    private String status;

    public CustomerRequest(String requestId, String requestDetails,
                           String requestDate, String status) {

        this.requestId = requestId;
        this.requestDetails = requestDetails;
        this.requestDate = requestDate;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
