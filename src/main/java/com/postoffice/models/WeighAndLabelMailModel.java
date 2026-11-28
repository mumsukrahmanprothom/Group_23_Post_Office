/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 4: Weigh and Label Mail model. */
public class WeighAndLabelMailModel implements Serializable {
    private String mailId;
    private double weight;
    private String postageRate;
    private String label;
    private String dateLabeled;
    private String status;

    public WeighAndLabelMailModel(String mailId, double weight, String postageRate,
                                  String label, String dateLabeled, String status) {
        this.mailId = mailId;
        this.weight = weight;
        this.postageRate = postageRate;
        this.label = label;
        this.dateLabeled = dateLabeled;
        this.status = status;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getPostageRate() { return postageRate; }
    public void setPostageRate(String postageRate) { this.postageRate = postageRate; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getDateLabeled() { return dateLabeled; }
    public void setDateLabeled(String dateLabeled) { this.dateLabeled = dateLabeled; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Mail " + mailId + " | Weight: " + weight + "kg | Postage: " + postageRate;
    }
}