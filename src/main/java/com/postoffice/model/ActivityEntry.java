package com.postoffice.model;

import com.postoffice.util.Dates;

public class ActivityEntry {
    private final String timestamp;
    private final String username;
    private final String role;
    private final String action;

    public ActivityEntry(String username, String role, String action) {
        this(Dates.nowWithSeconds(), username, role, action);
    }

    public ActivityEntry(String timestamp, String username, String role, String action) {
        this.timestamp = timestamp;
        this.username = username;
        this.role = role;
        this.action = action;
    }

    public String getTimestamp() { return timestamp; }
    public String getUsername()  { return username; }
    public String getRole()      { return role; }
    public String getAction()    { return action; }

    public String toFileLine() {
        return timestamp + " | " + username + " | " + role + " | " + action;
    }
}
