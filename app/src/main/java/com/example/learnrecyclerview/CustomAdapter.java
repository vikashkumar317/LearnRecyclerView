package com.example.learnrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHoder>{
    private final ArrayList<Info> mInfoList;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<Info> infoList) {
        mInflater = LayoutInflater.from(context);
        mInfoList = infoList;
    }

    @Override
    public CustomAdapter.CustomViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHoder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHoder holder, int position) {
        Info current = mInfoList.get(position);
        holder.nameView.setText(current.getName());
        holder.dobView.setText(current.getDob());
        holder.addressView.setText(current.getAddress());
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    public class CustomViewHoder extends RecyclerView.ViewHolder {
        public final TextView nameView, dobView, addressView;
        public final ImageView imageView;
        final CustomAdapter mAdapter;

        public CustomViewHoder(@NonNull View itemView, CustomAdapter adapter) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTextView);
            dobView = itemView.findViewById(R.id.dobTextView);
            addressView = itemView.findViewById(R.id.addressTextView);
            imageView = itemView.findViewById(R.id.birdImageView);
            this.mAdapter = adapter;
        }
    }
}
