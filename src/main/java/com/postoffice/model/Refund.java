package com.postoffice.model;

import com.postoffice.util.Ui;

public class Refund {
    public static final String PENDING = "Pending";
    public static final String VERIFIED = "Verified";
    public static final String APPROVED = "Approved";
    public static final String REJECTED = "Rejected";

    private final String id, date, customer, reason;
    private final double amount;
    private String status = PENDING;
    private String verifiedBy = "";
    private String decidedBy = "";

    public Refund(String id, String date, String customer, double amount, String reason) {
        this.id = id; this.date = date; this.customer = customer;
        this.amount = amount; this.reason = reason;
    }

    public void verify(String by)  { status = VERIFIED; verifiedBy = by; }
    public void approve(String by) { status = APPROVED; decidedBy = by; }
    public void reject(String by)  { status = REJECTED; decidedBy = by; }
    public boolean isOpen()        { return PENDING.equals(status) || VERIFIED.equals(status); }

    public String getId()         { return id; }
    public String getDate()       { return date; }
    public String getCustomer()   { return customer; }
    public double getAmount()     { return amount; }
    public String getAmountText() { return Ui.money(amount); }
    public String getReason()     { return reason; }
    public String getStatus()     { return status; }
    public String getVerifiedBy() { return verifiedBy; }
    public String getDecidedBy()  { return decidedBy; }
}
