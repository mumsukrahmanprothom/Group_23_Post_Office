/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 7: Post Office Delivery Schedule model. */
public class PostOfficeDeliveryScheduleModel implements Serializable {
    private String parcelId;
    private String destination;
    private String deliveryDate;
    private String deliveryTime;
    private String carrier;
    private String status;

    public PostOfficeDeliveryScheduleModel(String parcelId, String destination, String deliveryDate,
                                           String deliveryTime, String carrier, String status) {
        this.parcelId = parcelId;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.carrier = carrier;
        this.status = status;
    }

    public String getParcelId() { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }
    public String getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }
    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Parcel " + parcelId + " | Destination: " + destination + " | Delivery: " + deliveryDate;
    }
}