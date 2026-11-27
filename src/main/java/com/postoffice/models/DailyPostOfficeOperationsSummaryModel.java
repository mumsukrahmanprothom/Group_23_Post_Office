/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 8: Daily Post Office Operations Summary model. */
public class DailyPostOfficeOperationsSummaryModel implements Serializable {
    private String date;
    private String totalLettersReceived;
    private String totalParcelsDispatched;
    private String totalCustomerInteracted;
    private String revenue;
    private String status;

    public DailyPostOfficeOperationsSummaryModel(String date, String totalLettersReceived,
                                                String totalParcelsDispatched,
                                                String totalCustomerInteracted, String revenue,
                                                String status) {
        this.date = date;
        this.totalLettersReceived = totalLettersReceived;
        this.totalParcelsDispatched = totalParcelsDispatched;
        this.totalCustomerInteracted = totalCustomerInteracted;
        this.revenue = revenue;
        this.status = status;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTotalLettersReceived() { return totalLettersReceived; }
    public void setTotalLettersReceived(String totalLettersReceived) { this.totalLettersReceived = totalLettersReceived; }
    public String getTotalParcelsDispatched() { return totalParcelsDispatched; }
    public void setTotalParcelsDispatched(String totalParcelsDispatched) { this.totalParcelsDispatched = totalParcelsDispatched; }
    public String getTotalCustomerInteracted() { return totalCustomerInteracted; }
    public void setTotalCustomerInteracted(String totalCustomerInteracted) { this.totalCustomerInteracted = totalCustomerInteracted; }
    public String getRevenue() { return revenue; }
    public void setRevenue(String revenue) { this.revenue = revenue; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Date: " + date + " | Letters: " + totalLettersReceived + " | Parcels: " + totalParcelsDispatched;
    }
}