package com.example.corphan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corphan.R;
import com.example.corphan.model.clothesDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class clothrecycler extends FirestoreRecyclerAdapter<clothesDetails,clothrecycler.clothHolder> {
    Context context;

    public clothrecycler(@NonNull FirestoreRecyclerOptions<clothesDetails> options,Context context) {
        super(options);
        this.context=context;
    }


    @Override
    protected void onBindViewHolder(@NonNull clothHolder holder, int position, @NonNull clothesDetails clothesD) {
        holder.usertitle.setText(clothesD.getName());
        holder.Mclothes.setText(clothesD.getMenClothes());
        holder.Wclothes.setText(clothesD.getWomenClothes());
        holder.Pdate.setText(clothesD.getPickupDate());
        holder.timestampTextView.setText(FirebaseUtil.timestampToString(clothesD.getCreatedTimeStamp()));
    }

    @NonNull
    @Override
    public clothrecycler.clothHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_clothes,parent,false);
        return new clothHolder(view);
    }
    class clothHolder extends RecyclerView.ViewHolder{
        TextView usertitle,Mclothes,Wclothes,Pdate,timestampTextView;

        public clothHolder(@NonNull View itemView) {
            super(itemView);
            usertitle=itemView.findViewById(R.id.nameC);
            Mclothes=itemView.findViewById(R.id.menClothes);
            Wclothes=itemView.findViewById(R.id.womenClothes);
            Pdate=itemView.findViewById(R.id.pickupDate);
            timestampTextView=itemView.findViewById(R.id.book_date);
        }

    }
}
