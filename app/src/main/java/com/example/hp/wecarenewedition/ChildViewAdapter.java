package com.example.hp.wecarenewedition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.ikurenewedition.R;

import java.util.List;

public class ChildViewAdapter extends RecyclerView.Adapter<ChildViewAdapter.MyViewHolder> {
    List<ChildViewData> childViewData;
    Context mContext;
    public ChildViewAdapter(Context mContext,List<ChildViewData>childViewData){
        this.mContext = mContext;
        this.childViewData = childViewData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chilld_card,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ChildViewData all = childViewData.get(position);
        holder.imageItem.setImageResource(all.mImageIdl);
        holder.textView.setText(all.mServiceType);
        holder.textView1.setText(all.mCheck);
        holder.textView2.setText(all.mCheckup);
        holder.textView3.setText(all.mLastCheckup);
        holder.textView4.setText(all.mTimeTillLastCheckup);
        holder.textView5.setText(all.mRemarks);
        holder.textView6.setText(all.mRemarksType);
        holder.textView7.setText(all.mPredicted);
        holder.textView8.setText(all.mPredictedValue);
        holder.textView9.setText(all.randomSugar);
        holder.textView10.setText(all.randomSugarValue);
    }
    @Override
    public int getItemCount() {
        return childViewData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        RelativeLayout relativeLayout;
        CardView cardView;
        ImageView imageItem;
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.childViewImage);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            textView = itemView.findViewById(R.id.serviceType);
            textView1 = itemView.findViewById(R.id.last_check);
            textView2 = itemView.findViewById(R.id.lastCheckup);
            textView3= itemView.findViewById(R.id.random_sugar);
            textView4 = itemView.findViewById(R.id.random_sugarValue);
            textView5 = itemView.findViewById(R.id.sinceLastCheckup);
            textView6 = itemView.findViewById(R.id.timetilllast_checkup);
            textView7 = itemView.findViewById(R.id.remarks);
            textView8 = itemView.findViewById(R.id.remarks_type);
            textView9 = itemView.findViewById(R.id.predicted_randomSugar);
            textView10 = itemView.findViewById(R.id.predicted_randomSugarValue);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
