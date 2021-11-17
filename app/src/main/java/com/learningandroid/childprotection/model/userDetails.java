package com.learningandroid.childprotection.model;

import android.location.Location;
import java.util.List;

public class userDetails {
    private String Contact;
    private String GroupId;
    private String Name;
    private String Role;

//    public userDetails(String contact, String groupId, android.location.Location location, String name, String role, List<statsItem> stats) {
//        Contact = contact;
//        GroupId = groupId;
//        Location = location;
//        Name = name;
//        Role = role;
//        Stats = stats;
//    }

    @Override
    public String toString() {
        return "userDetails{" +
                "Contact='" + Contact + '\'' +
                ", GroupId='" + GroupId + '\'' +
                ", Name='" + Name + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }

    public userDetails() {

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

}
