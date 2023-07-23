package com.example.corphan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.corphan.adapter.clothrecycler;
import com.example.corphan.adapter.edurecycler;
import com.example.corphan.model.clothesDetails;
import com.example.corphan.model.educDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class addEduc extends AppCompatActivity {
    FloatingActionButton add;
    RecyclerView recyclerView;

    edurecycler edurecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_educ);
        add=findViewById(R.id.addBtn);
        recyclerView=findViewById(R.id.recyler_view);
        setupRecyclerView();
        add.setOnClickListener((v -> startActivity(new Intent(addEduc.this, education.class))));
    }
    void setupRecyclerView(){
        Query query= FirebaseUtil.eduEvents().getParent().orderBy("createdTimeStamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<educDetails> options=new FirestoreRecyclerOptions.Builder<educDetails>().setQuery(query,educDetails.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        edurecycler=new edurecycler(options,this);
        recyclerView.setAdapter(edurecycler);
    }
    @Override
    protected void onStart() {
        super.onStart();
        edurecycler.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        edurecycler.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        edurecycler.notifyDataSetChanged();
    }
}