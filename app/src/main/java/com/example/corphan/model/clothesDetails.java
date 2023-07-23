package com.example.corphan.model;

import com.google.firebase.Timestamp;

public class clothesDetails {
    private String name;
    private Timestamp createdTimeStamp;
    private String menClothes;
    private String womenClothes;
    private String pickupDate;

    public clothesDetails() {
    }

    public clothesDetails(String name, Timestamp createdTimeStamp, String menClothes, String womenClothes, String pickupDate) {
        this.name = name;
        this.createdTimeStamp = createdTimeStamp;
        this.menClothes = menClothes;
        this.womenClothes = womenClothes;
        this.pickupDate = pickupDate;
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

    public String getMenClothes() {
        return menClothes;
    }

    public void setMenClothes(String menClothes) {
        this.menClothes = menClothes;
    }

    public String getWomenClothes() {
        return womenClothes;
    }

    public void setWomenClothes(String womenClothes) {
        this.womenClothes = womenClothes;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }
}
