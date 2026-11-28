/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 3: Outgoing Mail Dispatch model. */
public class OutgoingMailDispatchModel implements Serializable {
    private String parcelId;
    private String recipientName;
    private String recipientAddress;
    private String weight;
    private String serviceType;
    private String status;

    public OutgoingMailDispatchModel(String parcelId, String recipientName, String recipientAddress,
                                     String weight, String serviceType, String status) {
        this.parcelId = parcelId;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.weight = weight;
        this.serviceType = serviceType;
        this.status = status;
    }

    public String getParcelId() { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    public String getRecipientAddress() { return recipientAddress; }
    public void setRecipientAddress(String recipientAddress) { this.recipientAddress = recipientAddress; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Parcel " + parcelId + " -> " + recipientName + " (" + recipientAddress + ")";
    }
}