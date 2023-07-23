package com.example.corphan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corphan.model.stationeryDetails;
import com.example.corphan.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

public class books extends AppCompatActivity {

    EditText username,noteBooks,penD,tClass,tName;
    Button saveBtn;

    stationeryDetails stationeryD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        username=findViewById(R.id.nameB);
        noteBooks=findViewById(R.id.book);
        penD=findViewById(R.id.pensDetails);
        tClass=findViewById(R.id.textBook);
        tName=findViewById(R.id.textName);
        saveBtn=findViewById(R.id.save);
        saveBtn.setOnClickListener((view -> {
            statoinerydata();
        }));
    }
    void statoinerydata(){
        String userName=username.getText().toString();
        String nBooks=noteBooks.getText().toString();
        String penDetail=penD.getText().toString();
        String txtClass=tClass.getText().toString();
        String txtBook=tName.getText().toString();

        if(userName==null||userName.isEmpty()){
            username.setError("Enter the Name");
            return;
        }
        if(nBooks==null||nBooks.isEmpty()){
            noteBooks.setError("Enter the Name");
            return;
        }
        if(penDetail==null||penDetail.isEmpty()){
            penD.setError("Enter the Name");
            return;
        }
        if(txtClass==null||txtClass.isEmpty()){
            tClass.setError("Enter the Name");
            return;
        }
        if(txtBook==null||txtBook.isEmpty()){
            tName.setError("Enter the Name");
            return;
        }

        stationeryD =new stationeryDetails();
        stationeryD.setNameB(userName);
        stationeryD.setBooks(nBooks);
        stationeryD.setPens(penDetail);
        stationeryD.setTextClass(txtClass);
        stationeryD.setTextbooks(txtBook);
        stationeryD.setCreatedTimeStamp(Timestamp.now());


        FirebaseUtil.stationEvents().set(stationeryD).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(books.this,"Event is added Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(books.this,"Failed to add Event",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}