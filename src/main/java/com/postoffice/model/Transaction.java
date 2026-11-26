package com.postoffice.model;

import com.postoffice.util.Ui;

public class Transaction {
    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String REJECTED = "Rejected";

    private final String id, date, type, customer, createdBy;
    private final double amount;
    private String status = PENDING;
    private String decidedBy = "";

    public Transaction(String id, String date, String type, String customer,
                       double amount, String createdBy) {
        this.id = id; this.date = date; this.type = type; this.customer = customer;
        this.amount = amount; this.createdBy = createdBy;
    }

    public void approve(String by) { status = APPROVED; decidedBy = by; }
    public void reject(String by)  { status = REJECTED; decidedBy = by; }

    public String getId()        { return id; }
    public String getDate()      { return date; }
    public String getType()      { return type; }
    public String getCustomer()  { return customer; }
    public String getCreatedBy() { return createdBy; }
    public double getAmount()    { return amount; }
    public String getAmountText(){ return Ui.money(amount); }
    public String getStatus()    { return status; }
    public String getDecidedBy() { return decidedBy; }
}
