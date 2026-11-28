package com.postoffice.service;

import com.postoffice.model.*;
import com.postoffice.util.Dates;
import com.postoffice.util.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportService {
    public static final String DAILY = "Daily Report";
    public static final String MANAGEMENT = "Management Report";
    public static final String FINANCIAL = "Financial Report";

    public static String build(String type) {
        if (MANAGEMENT.equals(type)) return management();
        if (FINANCIAL.equals(type)) return financial();
        return daily();
    }

    private static StringBuilder header(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("POST OFFICE - ").append(title.toUpperCase()).append("\n");
        sb.append("Generated: ").append(Dates.now()).append("  by ").append(Session.username()).append("\n");
        sb.append(line('=', 60)).append("\n\n");
        return sb;
    }

    public static String daily() {
        DataStore s = DataStore.getInstance();
        String today = Dates.today();
        StringBuilder sb = header(DAILY + " (" + today + ")");

        int txToday = 0, pending = 0;
        for (Transaction t : s.getTransactions()) {
            if (t.getDate().equals(today)) txToday++;
            if (Transaction.PENDING.equals(t.getStatus())) pending++;
        }
        int mailIn = 0, parcelIn = 0, delivered = 0, unverified = 0;
        for (MailItem m : s.getMail()) {
            if (m.isInProgress()) { if (MailItem.MAIL.equals(m.getCategory())) mailIn++; else parcelIn++; }
            if (MailItem.DELIVERED.equals(m.getStatus())) { delivered++; if (!m.isVerified()) unverified++; }
        }
        int present = 0, late = 0, absent = 0;
        for (AttendanceRecord a : s.getAttendance()) {
            if (!a.getDate().equals(today)) continue;
            if (AttendanceRecord.PRESENT.equals(a.getStatus())) present++;
            else if (AttendanceRecord.LATE.equals(a.getStatus())) late++;
            else absent++;
        }
        int openDuties = 0;
        for (Duty d : s.getDuties()) if (!Duty.DONE.equals(d.getStatus())) openDuties++;
        int openComplaints = 0;
        for (Complaint c : s.getComplaints()) if (!Complaint.RESOLVED.equals(c.getStatus())) openComplaints++;
        int openRefunds = 0;
        for (Refund r : s.getRefunds()) if (r.isOpen()) openRefunds++;

        sb.append("TRANSACTIONS\n");
        sb.append("  Recorded today        : ").append(txToday).append("\n");
        sb.append("  Waiting for approval  : ").append(pending).append("\n\n");
        sb.append("MAIL & PARCELS\n");
        sb.append("  Mail in progress      : ").append(mailIn).append("\n");
        sb.append("  Parcels in progress   : ").append(parcelIn).append("\n");
        sb.append("  Delivered             : ").append(delivered).append(" (").append(unverified).append(" not yet verified)\n\n");
        sb.append("MONEY (today)\n");
        sb.append("  Revenue               : ").append(Ui.money(s.total(FinanceRecord.REVENUE, today))).append("\n");
        sb.append("  Expenses              : ").append(Ui.money(s.total(FinanceRecord.EXPENSE, today))).append("\n\n");
        sb.append("STAFF (today)\n");
        sb.append("  Present / Late / Absent : ").append(present).append(" / ").append(late).append(" / ").append(absent).append("\n");
        sb.append("  Duties not finished   : ").append(openDuties).append("\n\n");
        sb.append("OPEN ITEMS\n");
        sb.append("  Complaints            : ").append(openComplaints).append("\n");
        sb.append("  Refund requests       : ").append(openRefunds).append("\n");
        return sb.toString();
    }

    public static String financial() {
        DataStore s = DataStore.getInstance();
        StringBuilder sb = header(FINANCIAL);
        double rev = s.total(FinanceRecord.REVENUE, null);
        double exp = s.total(FinanceRecord.EXPENSE, null);
        sb.append("Total revenue : ").append(Ui.money(rev)).append("\n");
        sb.append("Total expenses: ").append(Ui.money(exp)).append("\n");
        sb.append("Net           : ").append(Ui.money(rev - exp)).append("\n\n");
        sb.append(left("Date", 12)).append(" ").append(left("Type", 9)).append(" ")
          .append(left("Category", 18)).append(" ").append(right("Amount", 12))
          .append("  Description\n");
        sb.append(line('-', 80)).append("\n");
        for (FinanceRecord f : s.getFinance()) {
            sb.append(left(f.getDate(), 12)).append(" ").append(left(f.getType(), 9)).append(" ")
              .append(left(f.getCategory(), 18)).append(" ").append(right(f.getAmountText(), 12))
              .append("  ").append(f.getDescription()).append("\n");
        }
        return sb.toString();
    }

    public static String management() {
        StringBuilder sb = new StringBuilder();
        sb.append(daily()).append("\n\n");
        sb.append(financial());
        return sb.toString();
    }

    public static File save(String type, String text) throws IOException {
        File folder = new File("reports");
        folder.mkdirs();
        String stamp = Dates.now().replace(" ", "_").replace(":", "-");
        File file = new File(folder, type.replace(" ", "_") + "_" + stamp + ".txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.print(text);
        writer.close();
        return file;
    }

    private static String line(char c, int count) {
        String result = "";
        for (int i = 0; i < count; i++) result = result + c;
        return result;
    }

    private static String left(String text, int width) {
        if (text.length() > width) return text.substring(0, width);
        while (text.length() < width) text = text + " ";
        return text;
    }

    private static String right(String text, int width) {
        while (text.length() < width) text = " " + text;
        return text;
    }
}
