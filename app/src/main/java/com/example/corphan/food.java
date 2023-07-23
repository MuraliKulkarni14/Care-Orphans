package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corphan.model.foodDetails;
import com.example.corphan.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class food extends AppCompatActivity {

    EditText username, event, eventDate;

    foodDetails foodD;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        username=findViewById(R.id.name);
        event=findViewById(R.id.occation);
        eventDate=findViewById(R.id.date);

        saveBtn=findViewById(R.id.save);

        saveBtn.setOnClickListener((view -> {
            foodData();
        }));
    }
    void foodData(){
        String userName=username.getText().toString();
        String userEvent=event.getText().toString();
        String eDate=eventDate.getText().toString();
        if(userName==null||userName.isEmpty()){
            username.setError("Enter the Name");
            return;
        }
        if(userEvent==null||userEvent.isEmpty()){
            event.setError("Enter the Name");
            return;
        }
        if(eDate==null||eDate.isEmpty()){
            eventDate.setError("Enter the Name");
            return;
        }
        foodD=new foodDetails();
        foodD.setName(userName);
        foodD.setOccation(userEvent);
        foodD.setDateOccation(eDate);
        foodD.setCreatedTimeStamp(Timestamp.now());

        FirebaseUtil.foodEvents().set(foodD).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(food.this,"Event is added Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(food.this,"Failed to add Event",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}