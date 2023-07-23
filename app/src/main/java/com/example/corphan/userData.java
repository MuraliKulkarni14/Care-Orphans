package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.corphan.model.UserModel;
import com.example.corphan.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class userData extends AppCompatActivity {
    EditText username, fullname, dob, contact;
    Button saveData;
    UserModel userModel;
    private String Username,Contact, Fullname, Dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        username=findViewById(R.id.myusername);
        fullname=findViewById(R.id.name);
        dob=findViewById(R.id.birthday);
        contact=findViewById(R.id.phone);
        saveData=findViewById(R.id.save);
        AuthuserData();
        saveData.setOnClickListener((view -> {
            setUsername();
        }));
    }
    boolean setUsername(){
        String usernameS=username.getText().toString();
        String fullnameS=fullname.getText().toString();
        String DOB=dob.getText().toString();
        String contactS=contact.getText().toString();
        if(usernameS.isEmpty()||usernameS.length()<3) {
            username.setError("Username length should be atleast 4 charecters");
        }
        if(userModel!=null){
            userModel.setUsername(usernameS);
            userModel.setFullname(fullnameS);
            userModel.setDob(DOB);
            userModel.setContact(contactS);
        }
        else{
            userModel=new UserModel( Contact, Username,Timestamp.now(), Fullname, Dob);
            return true;
        }
        FirebaseUtil.curr_user().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(userData.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
        return false;
    }
    void AuthuserData(){
        FirebaseUtil.curr_user().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    userModel=task.getResult().toObject(UserModel.class);
                    if(userModel!=null){
                        username.setText(userModel.getUsername());
                        fullname.setText(userModel.getFullname());
                        dob.setText(userModel.getDob());
                        contact.setText(userModel.getContact());
                    }
                }
            }
        });
    }
}