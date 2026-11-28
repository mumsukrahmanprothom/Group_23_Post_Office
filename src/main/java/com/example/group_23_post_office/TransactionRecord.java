package com.example.group_23_post_office;

public class TransactionRecord extends PostalRecord {
    private static final long serialVersionUID = 1L;
    private String description;
    private String reference;
    private String transactionType;
    private double amount;

    public TransactionRecord(int id, String description, String reference, String transactionType, double amount) {
        super(id, "Transaction", "Pending");
        this.description = description;
        this.reference = reference;
        this.transactionType = transactionType;
        this.amount = amount;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getSummary() { return description; }
    public String getDetails() { return "Description: " + description + " | Reference: " + reference + " | Type: " + transactionType + " | Amount: " + amount; }
}
