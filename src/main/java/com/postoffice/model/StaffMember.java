package com.postoffice.model;

public class StaffMember {
    private final String name, position;

    public StaffMember(String name, String position) {
        this.name = name; this.position = position;
    }

    public String getName()     { return name; }
    public String getPosition() { return position; }
}
