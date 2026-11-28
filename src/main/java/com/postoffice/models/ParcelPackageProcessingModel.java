/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 4: Parcel & Package Processing model. */
public class ParcelPackageProcessingModel implements Serializable {
    private String trackingNumber;
    private String parcelType;
    private double weight;
    private String destination;
    private String service;
    private String status;

    public ParcelPackageProcessingModel(String trackingNumber, String parcelType, double weight,
                                        String destination, String service, String status) {
        this.trackingNumber = trackingNumber;
        this.parcelType = parcelType;
        this.weight = weight;
        this.destination = destination;
        this.service = service;
        this.status = status;
    }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getParcelType() { return parcelType; }
    public void setParcelType(String parcelType) { this.parcelType = parcelType; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Tracking: " + trackingNumber + " | Parcels: " + parcelType + " | Weight: " + weight + "kg";
    }
}