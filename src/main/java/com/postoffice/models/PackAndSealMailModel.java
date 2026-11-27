/* <license-header /> */
package com.postoffice.models;

import java.io.Serializable;

/** Model class - User 8 Goal 6: Pack and Seal Mail model. */
public class PackAndSealMailModel implements Serializable {
    private String mailId;
    private String parcelType;
    private String packingMaterial;
    private String sealNumber;
    private String packagingDate;
    private String status;

    public PackAndSealMailModel(String mailId, String parcelType, String packingMaterial,
                                String sealNumber, String packagingDate, String status) {
        this.mailId = mailId;
        this.parcelType = parcelType;
        this.packingMaterial = packingMaterial;
        this.sealNumber = sealNumber;
        this.packagingDate = packagingDate;
        this.status = status;
    }

    public String getMailId() { return mailId; }
    public void setMailId(String mailId) { this.mailId = mailId; }
    public String getParcelType() { return parcelType; }
    public void setParcelType(String parcelType) { this.parcelType = parcelType; }
    public String getPackingMaterial() { return packingMaterial; }
    public void setPackingMaterial(String packingMaterial) { this.packingMaterial = packingMaterial; }
    public String getSealNumber() { return sealNumber; }
    public void setSealNumber(String sealNumber) { this.sealNumber = sealNumber; }
    public String getPackagingDate() { return packagingDate; }
    public void setPackagingDate(String packagingDate) { this.packagingDate = packagingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Mail " + mailId + " | Packed with " + packingMaterial + " | Seal: " + sealNumber;
    }
}