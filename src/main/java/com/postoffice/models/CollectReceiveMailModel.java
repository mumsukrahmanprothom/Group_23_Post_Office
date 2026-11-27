/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 1: Collect and Receive Mail model. */
public class CollectReceiveMailModel implements Serializable {
    private String mailId;
    private String mailType;
    private String senderName;
    private String senderAddress;
    private String recipientName;
    private String recipientAddress;
    private String weight;
    private String status;

    public CollectReceiveMailModel(String mailId, String mailType, String senderName,
                                   String senderAddress, String recipientName, String recipientAddress,
                                   String weight, String status) {
        this.mailId = mailId;
        this.mailType = mailType;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.weight = weight;
        this.status = status;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public String getMailType() { return mailType; }
    public void setMailType(String mailType) { this.mailType = mailType; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public String getSenderAddress() { return senderAddress; }
    public void setSenderAddress(String senderAddress) { this.senderAddress = senderAddress; }
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    public String getRecipientAddress() { return recipientAddress; }
    public void setRecipientAddress(String recipientAddress) { this.recipientAddress = recipientAddress; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Mail " + mailId + " -> " + recipientName + " (" + recipientAddress + ")";
    }
}