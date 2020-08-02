package com.example.learnrecyclerview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHoder>{
    public static final int STORAGE_PERMISSION_CODE = 1;
    private List<Hero> mHeroes;
    private LayoutInflater mInflater;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private Context mContext;

    public CustomAdapter(Context context, List<Hero> heroes) {
        mInflater = LayoutInflater.from(context);
        mHeroes = heroes;
        mContext = context;
    }

    @Override
    public CustomAdapter.CustomViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHoder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHoder holder, int position) {
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.swipelayout, String.valueOf(position));
        viewBinderHelper.closeLayout(String.valueOf(position));

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
        private SwipeRevealLayout swipelayout;
        private Button saveBtn;

        public CustomViewHoder(@NonNull View itemView, CustomAdapter adapter) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTextView);
            teamView = itemView.findViewById(R.id.teamTextView);
            publisherView = itemView.findViewById(R.id.publisherTextView);
            heroImageView = itemView.findViewById(R.id.heroImageView);
            numberView = itemView.findViewById(R.id.numberTextView);
            swipelayout = itemView.findViewById(R.id.swipelayout);
            saveBtn = itemView.findViewById(R.id.saveBtn);
            this.mAdapter = adapter;

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(mContext, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                    }
                }
            });
        }
    }
}
