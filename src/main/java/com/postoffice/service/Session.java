package com.postoffice.service;

import com.postoffice.model.PostMaster;
import com.postoffice.model.User;

public final class Session {
    private static User currentUser;

    private Session() { }

    public static User getCurrentUser() { return currentUser; }
    public static void setCurrentUser(User user) { currentUser = user; }
    public static void clear() { currentUser = null; }

    public static boolean isPostMaster() { return currentUser instanceof PostMaster; }

    public static String username() { return currentUser == null ? "?" : currentUser.getUsername(); }

    public static void log(String action) {
        if (currentUser != null) ActivityLogger.getInstance().log(currentUser, action);
    }
}
