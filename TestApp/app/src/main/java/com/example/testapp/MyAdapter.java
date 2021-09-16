package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * author : wangzhirui
 * date : 2021/9/10
 * description :
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> mDatas;

    public MyAdapter(ArrayList<Integer> datas) {
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int num = mDatas.get(position);
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.tv.setText(String.valueOf(num));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text_content);
        }
    }
}
