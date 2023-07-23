package com.example.corphan.model;

import com.google.firebase.Timestamp;

public class foodDetails {
    private String name;
    private Timestamp createdTimeStamp;
    private String dateOccation;
    private String occation;

    public foodDetails() {
    }

    public foodDetails(String name, Timestamp createdTimeStamp, String dateOccation, String occation) {
        this.name = name;
        this.createdTimeStamp = createdTimeStamp;
        this.dateOccation = dateOccation;
        this.occation = occation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getDateOccation() {
        return dateOccation;
    }

    public void setDateOccation(String dateOccation) {
        this.dateOccation = dateOccation;
    }

    public String getOccation() {
        return occation;
    }

    public void setOccation(String occation) {
        this.occation = occation;
    }
}
