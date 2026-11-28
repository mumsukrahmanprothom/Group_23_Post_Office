package com.example.group_23_post_office;

public class PostalService {

    private String serviceId;
    private String serviceName;
    private String description;
    private boolean availability;
    private double serviceFee;

    public PostalService(String serviceId, String serviceName,
                         String description, boolean availability,
                         double serviceFee) {

        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.availability = availability;
        this.serviceFee = serviceFee;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }
}
