/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 2: Incoming Letters Sorting model. */
public class IncomingLettersSortingModel implements Serializable {
    private String letterId;
    private String sender;
    private String recipient;
    private String mailType;
    private String status;

    public IncomingLettersSortingModel(String letterId, String sender, String recipient,
                                       String mailType, String status) {
        this.letterId = letterId;
        this.sender = sender;
        this.recipient = recipient;
        this.mailType = mailType;
        this.status = status;
    }

    public String getLetterId() { return letterId; }
    public void setLetterId(String letterId) { this.letterId = letterId; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getMailType() { return mailType; }
    public void setMailType(String mailType) { this.mailType = mailType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Letter " + letterId + " | Sender: " + sender + " | Recipient: " + recipient;
    }
}