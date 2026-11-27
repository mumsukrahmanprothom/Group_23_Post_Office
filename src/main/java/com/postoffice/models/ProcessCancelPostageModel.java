/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 5: Process and Cancel Postage Stamps model. */
public class ProcessCancelPostageModel implements Serializable {
    private String mailId;
    private String cancellationDate;
    private String cancelReason;
    private String stampType;
    private String cancelMark;
    private String status;

    public ProcessCancelPostageModel(String mailId, String cancellationDate, String cancelReason,
                                     String stampType, String cancelMark, String status) {
        this.mailId = mailId;
        this.cancellationDate = cancellationDate;
        this.cancelReason = cancelReason;
        this.stampType = stampType;
        this.cancelMark = cancelMark;
        this.status = status;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public String getCancellationDate() { return cancellationDate; }
    public void setCancellationDate(String cancellationDate) { this.cancellationDate = cancellationDate; }
    public String getCancelReason() { return cancelReason; }
    public void setCancelReason(String cancelReason) { this.cancelReason = cancelReason; }
    public String getStampType() { return stampType; }
    public void setStampType(String stampType) { this.stampType = stampType; }
    public String getCancelMark() { return cancelMark; }
    public void setCancelMark(String cancelMark) { this.cancelMark = cancelMark; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Mail " + mailId + " | Stamps Cancelled on " + cancellationDate;
    }
}