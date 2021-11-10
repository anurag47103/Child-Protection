package com.learningandroid.childprotection.model;

import java.util.Arrays;
import java.util.List;

public class newReg {
    String grpId;
    List<String> name;
    List<String> number;
    List<Character> role;


    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getNumber() {
        return number;
    }

    public void setNumber(List<String> number) {
        this.number = number;
    }

    public List<Character> getRole() {
        return role;
    }

    public void setRole(List<Character> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "newReg{" +
                "grpId='" + grpId + '\'' +
                ", name=" + name +
                ", number=" + number +
                ", role=" + role +
                '}';
    }
}
