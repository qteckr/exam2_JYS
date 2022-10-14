package com.study.qtecexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<item> arrayList;

    public CustomAdapter(ArrayList<item> arrayList, Context context) {
        this.mContext = context;
        this.arrayList = arrayList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_num.setText(String.valueOf(arrayList.get(position).getNum1()));
        holder.tv_str.setText(arrayList.get(position).getStr1());
    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_num;
        TextView tv_str;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_num = itemView.findViewById(R.id.tv_num);
            this.tv_str = itemView.findViewById(R.id.tv_str);
        }
    }

}