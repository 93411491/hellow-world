package com.example.likedouyin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.likedouyin.R;
import com.example.likedouyin.bean.ViewBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.jvm.internal.markers.KMutableList;

/**
 * author : wangzhirui
 * date : 2021/9/10
 * description :
 */
public class FeedViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> mDatas;
    private Context mContext;

    public FeedViewAdapter(ArrayList<Integer> mDatas, Context context) {
        this.mDatas = mDatas;
        mContext = context;
//        this.mDatas = new ArrayList<>(10);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);
        VedioViewHolder vvh = new VedioViewHolder(view);
        return vvh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        int num = mDatas.get(position);
        VedioViewHolder myViewholder = (VedioViewHolder) holder;
        myViewholder.textView.setText(String.valueOf(num));
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty()) {
            return 0;
        }
        return mDatas.size();
    }

    class VedioViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        public VedioViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.content);
        }
    }

}
