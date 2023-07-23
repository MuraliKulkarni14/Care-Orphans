package com.example.corphan.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String Contact;
    private String Username;
    private Timestamp createdTimeStamp;
    private String Fullname;
    private String Dob;



    public UserModel() {
    }

    public UserModel(String Contact, String Username, Timestamp createdTimeStamp, String Fullname, String Dob) {
        this.Username = Username;
        this.createdTimeStamp = createdTimeStamp;
        this.Fullname = Fullname;
        this.Dob = Dob;
        this.Contact = Contact;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }
}
