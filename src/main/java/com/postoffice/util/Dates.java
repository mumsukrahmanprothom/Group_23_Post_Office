package com.postoffice.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Dates {
    public static String today() {
        return LocalDate.now().toString();
    }

    public static String daysAgo(int n) {
        return LocalDate.now().minusDays(n).toString();
    }

    public static String now() {
        String text = LocalDateTime.now().toString();
        return text.substring(0, 16).replace('T', ' ');
    }

    public static String nowWithSeconds() {
        String text = LocalDateTime.now().toString();
        return text.substring(0, 19).replace('T', ' ');
    }

    public static boolean looksLikeDate(String text) {
        if (text.length() != 10) return false;
        for (int i = 0; i < 10; i++) {
            char c = text.charAt(i);
            if (i == 4 || i == 7) {
                if (c != '-') return false;
            } else {
                if (c < '0' || c > '9') return false;
            }
        }
        return true;
    }
}
