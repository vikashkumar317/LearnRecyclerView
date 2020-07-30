package com.example.learnrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHoder>{
    private List<Hero> mHeroes;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, List<Hero> heroes) {
        mInflater = LayoutInflater.from(context);
        mHeroes = heroes;
    }

    @Override
    public CustomAdapter.CustomViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHoder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHoder holder, int position) {
        Hero current = mHeroes.get(position);
        holder.nameView.setText(current.getName());
        holder.teamView.setText(current.getTeam());
        holder.publisherView.setText(current.getPublisher());
        holder.numberView.setText(Integer.toString(position+1));
        Glide.with(holder.heroImageView.getContext()).load(current.getImageurl()).into(holder.heroImageView);
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public class CustomViewHoder extends RecyclerView.ViewHolder {
        public final TextView nameView, teamView, publisherView, numberView;
        public final ImageView heroImageView;
        final CustomAdapter mAdapter;

        public CustomViewHoder(@NonNull View itemView, CustomAdapter adapter) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTextView);
            teamView = itemView.findViewById(R.id.teamTextView);
            publisherView = itemView.findViewById(R.id.publisherTextView);
            heroImageView = itemView.findViewById(R.id.heroImageView);
            numberView = itemView.findViewById(R.id.numberTextView);
            this.mAdapter = adapter;
        }
    }
}
