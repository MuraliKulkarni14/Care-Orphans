package com.example.corphan.model;

import com.google.firebase.Timestamp;

public class stationeryDetails {

    private String nameB;
    private String books;
    private String pens;
    private String textbooks;
    private String textClass;
    private Timestamp createdTimeStamp;


    public stationeryDetails() {
    }

    public stationeryDetails(String nameB, String books, String pens, String textbooks, String textClass, Timestamp createdTimeStamp) {
        this.nameB=nameB;
        this.books = books;
        this.pens = pens;
        this.textbooks = textbooks;
        this.textClass = textClass;
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getPens() {
        return pens;
    }

    public void setPens(String pens) {
        this.pens = pens;
    }

    public String getTextbooks() {
        return textbooks;
    }

    public void setTextbooks(String textbooks) {
        this.textbooks = textbooks;
    }

    public String getTextClass() {
        return textClass;
    }

    public void setTextClass(String textClass) {
        this.textClass = textClass;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
