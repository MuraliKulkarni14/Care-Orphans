package com.example.corphan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corphan.R;
import com.example.corphan.model.educDetails;
import com.example.corphan.util.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class edurecycler extends FirestoreRecyclerAdapter<educDetails,edurecycler.eduHolder> {
    Context context;
    public edurecycler(@NonNull FirestoreRecyclerOptions<educDetails> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull eduHolder holder, int position, @NonNull educDetails educD) {
        holder.Tname.setText(educD.getTeacherName());
        holder.tsubject.setText(educD.getSubject());
        holder.sTime.setText(educD.getsTime());
        holder.cDuration.setText(educD.getDuration());
        holder.cDate.setText(educD.getDate());
        holder.timestampTextView.setText(FirebaseUtil.timestampToString(educD.getCreatedTimeStamp()));
    }

    @NonNull
    @Override
    public eduHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_edu,parent,false);
        return new eduHolder(view);
    }

    class eduHolder extends RecyclerView.ViewHolder{
        TextView Tname,tsubject,sTime,cDuration,cDate,timestampTextView;
        public eduHolder(@NonNull View itemView) {
            super(itemView);
            Tname=itemView.findViewById(R.id.nameT);
            tsubject=itemView.findViewById(R.id.tSubject);
            sTime=itemView.findViewById(R.id.timing);
            cDuration=itemView.findViewById(R.id.duration);
            cDate=itemView.findViewById(R.id.pickupDate);
            timestampTextView=itemView.findViewById(R.id.book_date);

        }
    }
}
