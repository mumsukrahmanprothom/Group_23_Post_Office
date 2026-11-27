/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 8: Deliver Letters & Parcels model. */
public class DeliverMailModel implements Serializable {
    private String mailId;
    private String recipientName;
    private String recipientAddress;
    private String recipientContact;
    private String deliveryDate;
    private String status;
    private String signature;

    public DeliverMailModel(String mailId, String recipientName, String recipientAddress,
                            String recipientContact, String deliveryDate, String status, String signature) {
        this.mailId = mailId;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientContact = recipientContact;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.signature = signature;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    public String getRecipientAddress() { return recipientAddress; }
    public void setRecipientAddress(String recipientAddress) { this.recipientAddress = recipientAddress; }
    public String getRecipientContact() { return recipientContact; }
    public void setRecipientContact(String recipientContact) { this.recipientContact = recipientContact; }
    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }

    @Override
    public String toString() {
        return "Mail " + mailId + " -> " + recipientName + " (" + recipientAddress + ")";
    }
}