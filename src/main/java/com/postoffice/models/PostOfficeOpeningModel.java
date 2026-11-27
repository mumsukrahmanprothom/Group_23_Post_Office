/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 5: Post Office Opening model. */
public class PostOfficeOpeningModel implements Serializable {
    private String openingTime;
    private String staffOnDuty;
    private String countersReady;
    private String equipmentReady;
    private String status;

    public PostOfficeOpeningModel(String openingTime, String staffOnDuty, String countersReady,
                                  String equipmentReady, String status) {
        this.openingTime = openingTime;
        this.staffOnDuty = staffOnDuty;
        this.countersReady = countersReady;
        this.equipmentReady = equipmentReady;
        this.status = status;
    }

    public String getOpeningTime() { return openingTime; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }
    public String getStaffOnDuty() { return staffOnDuty; }
    public void setStaffOnDuty(String staffOnDuty) { this.staffOnDuty = staffOnDuty; }
    public String getCountersReady() { return countersReady; }
    public void setCountersReady(String countersReady) { this.countersReady = countersReady; }
    public String getEquipmentReady() { return equipmentReady; }
    public void setEquipmentReady(String equipmentReady) { this.equipmentReady = equipmentReady; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Opening Time: " + openingTime + " | Staff: " + staffOnDuty;
    }
}