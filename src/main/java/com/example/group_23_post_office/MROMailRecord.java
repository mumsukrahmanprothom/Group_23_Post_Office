
package com.example.group_23_post_office;

public class MROMailRecord {

    private String trackingId;
    private String sender;
    private String receiver;
    private String mailType;
    private double weight;
    private double fee;
    private String status;
    private String registrationDate;

    public MROMailRecord(String trackingId, String sender, String receiver,
                      String mailType, double weight, double fee,
                      String status, String registrationDate) {

        this.trackingId = trackingId;
        this.sender = sender;
        this.receiver = receiver;
        this.mailType = mailType;
        this.weight = weight;
        this.fee = fee;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMailType() {
        return mailType;
    }

    public double getWeight() {
        return weight;
    }

    public double getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
