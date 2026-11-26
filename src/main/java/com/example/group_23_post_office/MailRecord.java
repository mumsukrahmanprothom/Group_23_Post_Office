package com.example.group_23_post_office;

public class MailRecord extends PostalRecord {
    private static final long serialVersionUID = 1L;
    private String senderInfo;
    private String receiverInfo;
    private String mailType;
    private double weight;
    private String direction;

    public MailRecord(int id, String senderInfo, String receiverInfo, String mailType, double weight, String direction) {
        super(id, "Mail", "Registered");
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
        this.mailType = mailType;
        this.weight = weight;
        this.direction = direction;
    }
    public String getSenderInfo() { return senderInfo; }
    public void setSenderInfo(String senderInfo) { this.senderInfo = senderInfo; }
    public String getReceiverInfo() { return receiverInfo; }
    public void setReceiverInfo(String receiverInfo) { this.receiverInfo = receiverInfo; }
    public String getMailType() { return mailType; }
    public void setMailType(String mailType) { this.mailType = mailType; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
    public String getSummary() { return senderInfo + " to " + receiverInfo; }
    public String getDetails() { return "Sender: " + senderInfo + " | Receiver: " + receiverInfo + " | Type: " + mailType + " | Weight: " + weight + " kg | " + direction; }
    public int getMailID() { return getRecordID(); }
}
