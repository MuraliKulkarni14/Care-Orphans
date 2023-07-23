package com.example.corphan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.corphan.adapter.clothrecycler;
import com.example.corphan.model.clothesDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class addClothes extends AppCompatActivity {

    FloatingActionButton add;

    RecyclerView recyclerView;
    clothrecycler clothrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        add = findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.recyler_view);

        setupRecyclerView();
        add.setOnClickListener((v) -> startActivity(new Intent(addClothes.this, clothes.class)));
    }
    void setupRecyclerView(){
        Query query= FirebaseUtil.clothesEvents().getParent().orderBy("createdTimeStamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<clothesDetails> options=new FirestoreRecyclerOptions.Builder<clothesDetails>().setQuery(query,clothesDetails.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clothrecycler=new clothrecycler(options,this);
        recyclerView.setAdapter(clothrecycler);
    }
    @Override
    protected void onStart() {
        super.onStart();
        clothrecycler.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clothrecycler.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clothrecycler.notifyDataSetChanged();
    }
}