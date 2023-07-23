package com.example.corphan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.corphan.adapter.foodrecycler;
import com.example.corphan.model.foodDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class addFood extends AppCompatActivity {
    FloatingActionButton add;
    RecyclerView recyclerView;
    foodrecycler foodrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        add=findViewById(R.id.addBtn);
        recyclerView=findViewById(R.id.recyler_view);

        setupRecyclerView();
        add.setOnClickListener((v)->startActivity(new Intent(addFood.this, food.class)));
    }

    void setupRecyclerView(){
        Query query= FirebaseUtil.foodEvents().getParent().orderBy("createdTimeStamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<foodDetails> options=new FirestoreRecyclerOptions.Builder<foodDetails>().setQuery(query,foodDetails.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodrecycler=new foodrecycler(options,this);
        recyclerView.setAdapter(foodrecycler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        foodrecycler.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foodrecycler.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        foodrecycler.notifyDataSetChanged();
    }
}