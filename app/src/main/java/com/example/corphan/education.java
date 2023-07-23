package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corphan.model.educDetails;
import com.example.corphan.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

public class education extends AppCompatActivity {

    EditText teacher,subject,STime,cDuration,date;
    Button saveBtn;

    educDetails educD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        teacher=findViewById(R.id.tName);
        subject=findViewById(R.id.sub);
        STime=findViewById(R.id.time);
        cDuration=findViewById(R.id.dur);
        date=findViewById(R.id.pdate);
        saveBtn=findViewById(R.id.save);
        saveBtn.setOnClickListener((view -> {
            eduData();
        }));
    }
    void eduData(){
        String teacherName=teacher.getText().toString();
        String tsubject=subject.getText().toString();
        String sTime=STime.getText().toString();
        String duration=cDuration.getText().toString();
        String Date=date.getText().toString();
        if(teacherName==null||teacherName.isEmpty()){
            teacher.setError("Enter the Name");
            return;
        }
        if(tsubject==null||tsubject.isEmpty()){
            subject.setError("Enter the Name");
            return;
        }
        if(sTime==null||sTime.isEmpty()){
            STime.setError("Enter the Name");
            return;
        }
        if(duration==null||duration.isEmpty()){
            cDuration.setError("Enter the Name");
            return;
        }
        if(Date==null||Date.isEmpty()){
            date.setError("Enter the Name");
            return;
        }
        educD=new educDetails();
        educD.setTeacherName(teacherName);
        educD.setSubject(tsubject);
        educD.setsTime(sTime);
        educD.setDuration(duration);
        educD.setDate(Date);
        educD.setCreatedTimeStamp(Timestamp.now());

        FirebaseUtil.eduEvents().set(educD).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(education.this,"Event is added Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(education.this,"Failed to add Event",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}