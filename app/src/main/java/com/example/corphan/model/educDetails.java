package com.example.corphan.model;

import com.google.firebase.Timestamp;

public class educDetails {
    private String teacherName;
    private Timestamp createdTimeStamp;
    private  String subject;
    private String sTime;
    private String date;
    private String duration;

    public educDetails() {
    }

    public educDetails(String teacherName, Timestamp createdTimeStamp, String subject, String sTime, String date, String duration) {
        this.teacherName = teacherName;
        this.createdTimeStamp = createdTimeStamp;
        this.subject = subject;
        this.sTime = sTime;
        this.date = date;
        this.duration = duration;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
