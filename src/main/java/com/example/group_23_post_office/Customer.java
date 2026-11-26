package com.example.group_23_post_office;

public class Customer extends PostalRecord {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String contact;

    public Customer(int id, String name, String address, String contact) {
        super(id, "Customer", "Active");
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getSummary() { return name; }
    public String getDetails() { return "Name: " + name + " | Address: " + address + " | Contact: " + contact; }
    public int getCustomerID() { return getRecordID(); }
}
