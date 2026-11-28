/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 1: Office Opening & Closing model. */
public class OfficeOpeningClosingModel implements Serializable {
    private String activity;
    private String timing;
    private String status;

    public OfficeOpeningClosingModel(String activity, String timing, String status) {
        this.activity = activity;
        this.timing = timing;
        this.status = status;
    }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }
    public String getTiming() { return timing; }
    public void setTiming(String timing) { this.timing = timing; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Activity: " + activity + " | Timing: " + timing + " | Status: " + status;
    }
}