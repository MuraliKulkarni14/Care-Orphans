package com.example.corphan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corphan.R;

import com.example.corphan.model.stationeryDetails;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class bookrecycler extends FirestoreRecyclerAdapter<stationeryDetails,bookrecycler.stationeryHolder> {

    Context context;
    public bookrecycler(@NonNull FirestoreRecyclerOptions<stationeryDetails> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull stationeryHolder holder, int position, @NonNull stationeryDetails stationeryD) {
        holder.usertitle.setText(stationeryD.getNameB());
        holder.notes.setText(stationeryD.getBooks());
        holder.pens.setText(stationeryD.getPens());
        holder.textbook.setText(stationeryD.getTextbooks());
        holder.textclass.setText(stationeryD.getTextClass());
    }

    @NonNull
    @Override
    public stationeryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_stationery,parent,false);
        return new stationeryHolder(view);
    }

    class stationeryHolder extends RecyclerView.ViewHolder{

            TextView usertitle,notes,pens,textclass,textbook;

            public stationeryHolder(@NonNull View itemView) {
                super(itemView);
                usertitle=itemView.findViewById(R.id.unameB);
                notes=itemView.findViewById(R.id.noNotes);
                pens=itemView.findViewById(R.id.noPens);
                textclass=itemView.findViewById(R.id.textBC);
                textbook=itemView.findViewById(R.id.subjectC);

            }
        }
}
