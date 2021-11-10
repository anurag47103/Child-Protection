package com.learningandroid.childprotection.model;

import java.util.List;

public class userDetails {
    String id;
    String name;
    String role;
    String phone; //primary key
    List<statsItem> statsItemList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<statsItem> getStatsItemList() {
        return statsItemList;
    }

    public void setStatsItemList(List<statsItem> statsItemList) {
        this.statsItemList = statsItemList;
    }

    @Override
    public String toString() {
        return "userDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", statsItemList=" + statsItemList +
                '}';
    }
}
