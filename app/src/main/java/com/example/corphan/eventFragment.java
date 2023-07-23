package com.example.corphan;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class eventFragment extends Fragment {


    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View root= inflater.inflate(R.layout.fragment_event, container, false);
        return root;
    }
    public void onStart(){
        super.onStart();
        CardView btnFund= context.findViewById(R.id.fund);
        CardView btnBooks=context.findViewById(R.id.books);
        CardView btnClothes=context.findViewById(R.id.clothes);
        CardView btnFood= context.findViewById(R.id.food);
        CardView btnEduc= context.findViewById(R.id.education);
        CardView btnGovt= context.findViewById(R.id.govt);



        btnFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, fund.class);
                startActivity(intent);
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, addFood.class);
                startActivity(intent);
            }
        });
        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, addStationery.class);
                startActivity(intent);
            }
        });
        btnClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, addClothes.class);
                startActivity(intent);
            }
        });
        btnEduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, addEduc.class);
                startActivity(intent);
            }
        });
        btnGovt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.nipccd.nic.in/schemes/national-children-fund-28#gsc.tab=0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setPackage("com.android.chrome"); // Use Chrome as the default browser
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Chrome is not installed, open with the default browser
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });
    }
}