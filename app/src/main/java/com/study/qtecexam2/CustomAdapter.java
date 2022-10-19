package com.study.qtecexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.tv_str.setText(arrayList.get(position).getStr1());
        holder.tv_str2.setText(arrayList.get(position).getStr2());

    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lay_item;
        TextView tv_str;
        TextView tv_str2;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lay_item = itemView.findViewById(R.id.lay_item);
            this.tv_str = itemView.findViewById(R.id.tv_str);
            this.tv_str2 = itemView.findViewById(R.id.tv_str2);

            lay_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(), "메뉴" + tv_str.getText() + " 클릭!", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

}