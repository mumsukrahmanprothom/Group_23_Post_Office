package com.postoffice.service;

import com.postoffice.model.*;
import com.postoffice.util.Dates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private final ObservableList<MailItem> mail = FXCollections.observableArrayList();
    private final ObservableList<Refund> refunds = FXCollections.observableArrayList();
    private final ObservableList<Complaint> complaints = FXCollections.observableArrayList();
    private final ObservableList<FinanceRecord> finance = FXCollections.observableArrayList();
    private final ObservableList<Duty> duties = FXCollections.observableArrayList();
    private final ObservableList<AttendanceRecord> attendance = FXCollections.observableArrayList();
    private final ObservableList<StaffMember> staff = FXCollections.observableArrayList();

    private int txCount = 1000, poCount = 1000, rfCount = 1000, cpCount = 1000;
    private int fnCount = 1000, dtCount = 1000;

    private DataStore() { seed(); }

    public static DataStore getInstance() { return INSTANCE; }

    public ObservableList<Transaction> getTransactions()   { return transactions; }
    public ObservableList<MailItem> getMail()              { return mail; }
    public ObservableList<Refund> getRefunds()             { return refunds; }
    public ObservableList<Complaint> getComplaints()       { return complaints; }
    public ObservableList<FinanceRecord> getFinance()      { return finance; }
    public ObservableList<Duty> getDuties()                { return duties; }
    public ObservableList<AttendanceRecord> getAttendance(){ return attendance; }
    public ObservableList<StaffMember> getStaff()          { return staff; }

    public String nextId(String prefix) {
        int number = 0;
        if (prefix.equals("TX")) { txCount++; number = txCount; }
        else if (prefix.equals("PO")) { poCount++; number = poCount; }
        else if (prefix.equals("RF")) { rfCount++; number = rfCount; }
        else if (prefix.equals("CP")) { cpCount++; number = cpCount; }
        else if (prefix.equals("FN")) { fnCount++; number = fnCount; }
        else if (prefix.equals("DT")) { dtCount++; number = dtCount; }
        return prefix + "-" + number;
    }

    public List<String> getStaffNames() {
        List<String> names = new ArrayList<>();
        for (StaffMember s : staff) names.add(s.getName());
        return names;
    }

    public StaffMember findStaff(String name) {
        for (StaffMember s : staff) if (s.getName().equals(name)) return s;
        return null;
    }

    public void addRevenue(String category, double amount, String description) {
        finance.add(0, new FinanceRecord(nextId("FN"), Dates.today(), FinanceRecord.REVENUE,
                category, amount, description));
    }

    public void addExpense(String category, double amount, String description) {
        finance.add(0, new FinanceRecord(nextId("FN"), Dates.today(), FinanceRecord.EXPENSE,
                category, amount, description));
    }

    public double total(String type, String date) {
        double sum = 0;
        for (FinanceRecord f : finance) {
            if (f.getType().equals(type) && (date == null || f.getDate().equals(date))) sum += f.getAmount();
        }
        return sum;
    }

    public double routineLimit() {
        return 500;
    }

    private void seed() {
        String today = Dates.today();

        staff.addAll(
                new StaffMember("Assistant Post Master", "Assistant Post Master"),
                new StaffMember("Rahim Uddin", "Counter Clerk"),
                new StaffMember("Sumaiya Akter", "Mail Sorter"),
                new StaffMember("Karim Hossain", "Postman"),
                new StaffMember("Nila Rahman", "Cashier"));

        Transaction t1 = new Transaction(nextId("TX"), today, "Stamp Sale", "Anwar Hossain", 120, "assistant");
        t1.approve("assistant");
        Transaction t2 = new Transaction(nextId("TX"), today, "Parcel Posting", "Mita Begum", 350, "assistant");
        Transaction t3 = new Transaction(nextId("TX"), today, "Money Order", "Jamal Khan", 2500, "assistant");
        Transaction t4 = new Transaction(nextId("TX"), today, "Registered Mail", "Salma Khatun", 80, "assistant");
        transactions.addAll(t4, t3, t2, t1);
        addRevenueOn(today, "Stamp Sale", 120, "Transaction " + t1.getId());

        MailItem m1 = new MailItem(nextId("PO"), MailItem.MAIL, "Anwar Hossain", "Rubel Ahmed", "Dhaka", "assistant");
        m1.advance("assistant");
        MailItem m2 = new MailItem(nextId("PO"), MailItem.PARCEL, "Mita Begum", "Shuvo Das", "Chattogram", "assistant");
        m2.advance("assistant"); m2.advance("assistant");
        MailItem m3 = new MailItem(nextId("PO"), MailItem.PARCEL, "Jamal Khan", "Lipi Akter", "Sylhet", "assistant");
        m3.advance("assistant"); m3.advance("assistant"); m3.advance("assistant");
        MailItem m4 = new MailItem(nextId("PO"), MailItem.MAIL, "Salma Khatun", "Nasir Uddin", "Khulna", "assistant");
        MailItem m5 = new MailItem(nextId("PO"), MailItem.PARCEL, "Tariq Aziz", "Farhana Islam", "Rajshahi", "assistant");
        m5.advance("assistant"); m5.advance("assistant"); m5.advance("assistant"); m5.verifyDelivery("assistant");
        mail.addAll(m5, m4, m3, m2, m1);

        refunds.addAll(
                new Refund(nextId("RF"), today, "Mita Begum", 60, "Parcel could not be accepted - wrong size"),
                new Refund(nextId("RF"), today, "Jamal Khan", 1200, "Money order cancelled by customer"));

        Complaint c1 = new Complaint(nextId("CP"), today, "Rubel Ahmed", "Late delivery",
                "Registered letter took 9 days to arrive.");
        Complaint c2 = new Complaint(nextId("CP"), Dates.daysAgo(1), "Lipi Akter", "Damaged parcel",
                "Box arrived crushed; contents scratched.");
        c2.setStatus(Complaint.IN_REVIEW);
        c2.addNote("assistant", "Photos received from customer.");
        complaints.addAll(c2, c1);

        addRevenueOn(Dates.daysAgo(2), "Parcel Posting", 1850, "Daily counter sales");
        addRevenueOn(Dates.daysAgo(2), "Stamp Sale", 640, "Daily counter sales");
        addExpenseOn(Dates.daysAgo(2), "Utilities", 700, "Electricity bill");
        addRevenueOn(Dates.daysAgo(1), "Parcel Posting", 2100, "Daily counter sales");
        addRevenueOn(Dates.daysAgo(1), "Money Order", 300, "Commission");
        addExpenseOn(Dates.daysAgo(1), "Supplies", 450, "Packing material");
        addExpenseOn(today, "Transport", 380, "Van fuel");

        duties.addAll(
                new Duty(nextId("DT"), "Rahim Uddin", "Handle counter 1", today, "postmaster"),
                new Duty(nextId("DT"), "Sumaiya Akter", "Sort morning mail", today, "assistant"),
                new Duty(nextId("DT"), "Karim Hossain", "Deliver Zone A parcels", today, "assistant"));
        duties.get(1).setStatus(Duty.DONE);
        duties.get(2).setStatus(Duty.IN_PROGRESS);

        for (String n : getStaffNames()) {
            attendance.add(new AttendanceRecord(Dates.daysAgo(1), n, AttendanceRecord.PRESENT, "assistant"));
        }
        attendance.add(new AttendanceRecord(today, "Assistant Post Master", AttendanceRecord.PRESENT, "postmaster"));
        attendance.add(new AttendanceRecord(today, "Rahim Uddin", AttendanceRecord.PRESENT, "assistant"));
        attendance.add(new AttendanceRecord(today, "Sumaiya Akter", AttendanceRecord.LATE, "assistant"));
        attendance.add(new AttendanceRecord(today, "Karim Hossain", AttendanceRecord.PRESENT, "assistant"));
        attendance.add(new AttendanceRecord(today, "Nila Rahman", AttendanceRecord.ABSENT, "assistant"));
    }

    private void addRevenueOn(String date, String category, double amount, String desc) {
        finance.add(new FinanceRecord(nextId("FN"), date, FinanceRecord.REVENUE, category, amount, desc));
    }

    private void addExpenseOn(String date, String category, double amount, String desc) {
        finance.add(new FinanceRecord(nextId("FN"), date, FinanceRecord.EXPENSE, category, amount, desc));
    }
}
