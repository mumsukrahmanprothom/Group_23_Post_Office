/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 7 Goal 6: Postal Equipment & Resources model. */
public class PostalEquipmentResourcesModel implements Serializable {
    private String equipmentName;
    private String status;
    private String lastMaintained;
    private String quantity;
    private String location;

    public PostalEquipmentResourcesModel(String equipmentName, String status, String lastMaintained,
                                         String quantity, String location) {
        this.equipmentName = equipmentName;
        this.status = status;
        this.lastMaintained = lastMaintained;
        this.quantity = quantity;
        this.location = location;
    }

    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getLastMaintained() { return lastMaintained; }
    public void setLastMaintained(String lastMaintained) { this.lastMaintained = lastMaintained; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Equipment: " + equipmentName + " | Status: " + status;
    }
}