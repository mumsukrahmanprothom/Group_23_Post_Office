package com.example.group_23_post_office;

import java.io.Serializable;
public class AccessPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private int permissionID, userID;
    private String accessLevel, status;
    public AccessPermission(int permissionID, int userID, String accessLevel, String status) {
        this.permissionID = permissionID; this.userID = userID;
        this.accessLevel = accessLevel; this.status = status;
    }
    public int getPermissionID() { return permissionID; }
    public int getUserID() { return userID; }
    public String getAccessLevel() { return accessLevel; }
    public String getStatus() { return status; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
    public void setStatus(String status) { this.status = status; }
    public boolean canRead() { return status.equals("Active") && !accessLevel.equals("No access"); }
    public boolean canWrite() { return canRead() && accessLevel.equals("Read and write"); }
    public String toString() { return "User " + userID + ": " + accessLevel + " / " + status; }
}
