package com.example.corphan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.corphan.adapter.bookrecycler;
import com.example.corphan.adapter.clothrecycler;
import com.example.corphan.model.clothesDetails;
import com.example.corphan.model.stationeryDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class addStationery extends AppCompatActivity {

    FloatingActionButton add;

    RecyclerView recyclerView;

    bookrecycler bookrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stationery);
        add = findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.recyler_view);
        setupRecyclerView();
        add.setOnClickListener((v) -> startActivity(new Intent(addStationery.this, books.class)));
    }
    void  setupRecyclerView(){
        Query query= FirebaseUtil.stationEvents().getParent().orderBy("createdTimeStamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<stationeryDetails> options=new FirestoreRecyclerOptions.Builder<stationeryDetails>().setQuery(query,stationeryDetails.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookrecycler=new bookrecycler(options,this);
        recyclerView.setAdapter(bookrecycler);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bookrecycler.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookrecycler.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookrecycler.notifyDataSetChanged();
    }
}