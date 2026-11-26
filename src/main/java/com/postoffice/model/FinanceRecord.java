package com.postoffice.model;

import com.postoffice.util.Ui;

public class FinanceRecord {
    public static final String REVENUE = "Revenue";
    public static final String EXPENSE = "Expense";

    private final String id, date, type, category, description;
    private final double amount;

    public FinanceRecord(String id, String date, String type, String category,
                         double amount, String description) {
        this.id = id; this.date = date; this.type = type; this.category = category;
        this.amount = amount; this.description = description;
    }

    public String getId()          { return id; }
    public String getDate()        { return date; }
    public String getType()        { return type; }
    public String getCategory()    { return category; }
    public double getAmount()      { return amount; }
    public String getAmountText()  { return Ui.money(amount); }
    public String getDescription() { return description; }
}
