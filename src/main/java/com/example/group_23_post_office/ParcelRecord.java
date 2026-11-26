package com.example.group_23_post_office;

public class ParcelRecord extends PostalRecord {
    private static final long serialVersionUID = 1L;
    private String senderInfo;
    private String receiverInfo;
    private String parcelType;
    private double weight;
    private String trackingNumber;

    public ParcelRecord(int id, String senderInfo, String receiverInfo, String parcelType, double weight, String trackingNumber) {
        super(id, "Parcel", "Registered");
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
        this.parcelType = parcelType;
        this.weight = weight;
        this.trackingNumber = trackingNumber;
    }
    public String getSenderInfo() { return senderInfo; }
    public void setSenderInfo(String senderInfo) { this.senderInfo = senderInfo; }
    public String getReceiverInfo() { return receiverInfo; }
    public void setReceiverInfo(String receiverInfo) { this.receiverInfo = receiverInfo; }
    public String getParcelType() { return parcelType; }
    public void setParcelType(String parcelType) { this.parcelType = parcelType; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getSummary() { return trackingNumber + " : " + senderInfo + " to " + receiverInfo; }
    public String getDetails() { return "Sender: " + senderInfo + " | Receiver: " + receiverInfo + " | Type: " + parcelType + " | Weight: " + weight + " kg | Tracking: " + trackingNumber; }
    public int getParcelID() { return getRecordID(); }
    public static String generateTrackingNo(int id) { return "PO-" + id; }
}
