package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corphan.model.clothesDetails;
import com.example.corphan.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

public class clothes extends AppCompatActivity {

    EditText username,boysClothes,girlsClothes,date;
    Button saveBtn;

    clothesDetails clothesD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        username=findViewById(R.id.name);
        boysClothes=findViewById(R.id.boys);
        girlsClothes=findViewById(R.id.girls);
        date=findViewById(R.id.pdate);
        saveBtn=findViewById(R.id.save1);
        saveBtn.setOnClickListener((view -> {
            clothesdata();
        }));
    }

    void clothesdata() {
        String userName=username.getText().toString();
        String boyclothes=boysClothes.getText().toString();
        String girlclothes=girlsClothes.getText().toString();
        String pickDate=date.getText().toString();
        if(userName==null||userName.isEmpty()){
            username.setError("Enter the Name");
            return;
        }
        if(boyclothes==null||boyclothes.isEmpty()){
            boysClothes.setError("Enter the Name");
            return;
        }
        if(girlclothes==null||girlclothes.isEmpty()){
            girlsClothes.setError("Enter the Name");
            return;
        }
        if(pickDate==null||pickDate.isEmpty()){
            date.setError("Enter the Name");
            return;
        }
        clothesD=new clothesDetails();
        clothesD.setName(userName);
        clothesD.setMenClothes(boyclothes);
        clothesD.setWomenClothes(girlclothes);
        clothesD.setPickupDate(pickDate);
        clothesD.setCreatedTimeStamp(Timestamp.now());

        FirebaseUtil.clothesEvents().set(clothesD).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(clothes.this,"Event is added Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(clothes.this,"Failed to add Event",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
