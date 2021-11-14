package com.learningandroid.childprotection.model;

import android.location.Location;
import java.util.List;

public class userDetails {
    private String Contact;
    private String GroupId;
    private Location Location;
    private String Name;
    private String Role;
    private List<statsItem> Stats;

    public userDetails(String contact, String groupId, android.location.Location location, String name, String role, List<statsItem> stats) {
        Contact = contact;
        GroupId = groupId;
        Location = location;
        Name = name;
        Role = role;
        Stats = stats;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public android.location.Location getLocation() {
        return Location;
    }

    public void setLocation(android.location.Location location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public List<statsItem> getStats() {
        return Stats;
    }

    public void setStats(List<statsItem> stats) {
        Stats = stats;
    }
}
