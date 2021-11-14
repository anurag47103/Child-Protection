package com.learningandroid.childprotection.model;

import java.util.HashMap;

public class userDetails {
    private String name;
    private String number;
    private Character role;
    private String grpId;
    private HashMap<String,String> usageStats;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public HashMap<String, String> getUsageStats() {
        return usageStats;
    }

    public void setUsageStats(HashMap<String, String> usageStats) {
        this.usageStats = usageStats;
    }

    public userDetails(String name, String number, Character role, String grpId, HashMap<String, String> usageStats) {
        this.name = name;
        this.number = number;
        this.role = role;
        this.grpId = grpId;
        this.usageStats = usageStats;
    }

    @Override
    public String toString() {
        return "userDetails{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", role=" + role +
                ", grpId='" + grpId + '\'' +
                ", usageStats=" + usageStats +
                '}';
    }
}
