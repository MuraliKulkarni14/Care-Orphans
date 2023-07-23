package com.example.corphan.util;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class FirebaseUtil {
    public static String curr_userID(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference curr_user(){
        return FirebaseFirestore.getInstance().collection("users").document(curr_userID()).collection("profile").document("profileData");
    }

    public static DocumentReference foodEvents(){
        return FirebaseFirestore.getInstance().collection("users").document(curr_userID()).collection("events").document("eventclasses").collection("food").document();
    }
    public static DocumentReference clothesEvents(){
        return FirebaseFirestore.getInstance().collection("users").document(curr_userID()).collection("events").document("eventclasses").collection("clothes").document();
    }

    public static DocumentReference stationEvents(){
        return FirebaseFirestore.getInstance().collection("users").document(curr_userID()).collection("events").document("eventclasses").collection("stationery").document();
    }

    public static DocumentReference eduEvents(){
        return FirebaseFirestore.getInstance().collection("users").document(curr_userID()).collection("events").document("eventclasses").collection("education").document();
    }
    public static String timestampToString(Timestamp timestamp){
        if (timestamp == null) {
          return "";
       }

        return new SimpleDateFormat("DD/MM/YYYY").format(timestamp.toDate());

    }
//    public static String timestampToServerTimeString(Timestamp timestamp) {
//        if (timestamp == null) {
//            return "";
//        }
//
//        String serverTimezoneID = "Asia/Kolkata";
//        TimeZone serverTimezone = TimeZone.getTimeZone(serverTimezoneID);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        sdf.setTimeZone(serverTimezone);
//
//        return sdf.format(timestamp.toDate());
//    }
}
