package com.example.corphan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corphan.R;
import com.example.corphan.model.foodDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class foodrecycler extends FirestoreRecyclerAdapter<foodDetails,foodrecycler.foodHolder> {
    Context context;

    public foodrecycler(@NonNull FirestoreRecyclerOptions<foodDetails> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull foodHolder holder, int position, @NonNull foodDetails foodD) {
        holder.titleOccation.setText(foodD.getOccation());
        holder.usertitle.setText(foodD.getName());
        holder.occationdate.setText(foodD.getDateOccation());
        holder.timestampTextView.setText(FirebaseUtil.timestampToString(foodD.getCreatedTimeStamp()));

    }

    @NonNull
    @Override
    public foodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_food,parent,false);
        return new foodHolder(view);
    }

    class foodHolder extends RecyclerView.ViewHolder{
        TextView titleOccation,usertitle,occationdate,timestampTextView;

        public foodHolder(@NonNull View itemView) {
            super(itemView);
            titleOccation=itemView.findViewById(R.id.occation);
            usertitle=itemView.findViewById(R.id.Uname);
            occationdate=itemView.findViewById(R.id.occDate);
            timestampTextView=itemView.findViewById(R.id.book_date);
        }

    }
}
