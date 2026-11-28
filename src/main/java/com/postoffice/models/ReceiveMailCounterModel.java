/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 2: Receive Letters & Parcels at Counter model. */
public class ReceiveMailCounterModel implements Serializable {
    private String receiptId;
    private String mailType;
    private String senderName;
    private String senderAddress;
    private String weight;
    private String service;
    private String status;

    public ReceiveMailCounterModel(String receiptId, String mailType, String senderName,
                                   String senderAddress, String weight, String service, String status) {
        this.receiptId = receiptId;
        this.mailType = mailType;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.weight = weight;
        this.service = service;
        this.status = status;
    }

    public String getReceiptId() { return receiptId; }
    public void setReceiptId(String receiptId) { this.receiptId = receiptId; }
    public String getMailType() { return mailType; }
    public void setMailType(String mailType) { this.mailType = mailType; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public String getSenderAddress() { return senderAddress; }
    public void setSenderAddress(String senderAddress) { this.senderAddress = senderAddress; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Receipt " + receiptId + " - " + mailType + " from " + senderName;
    }
}