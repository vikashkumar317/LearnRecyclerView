package com.example.learnrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private List<Hero> heroes;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Building Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create Class of Api Using Retrofit object
        Api api = retrofit.create(Api.class);

        // Make Call
        Call<List<Hero>> call = api.getHeroes();

        // Place Call
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroes = response.body();
                // Trying to increase no of data by making copy of it
                heroes.addAll(heroes);
                heroes.addAll(heroes);
                heroes.addAll(heroes);

                //Adding Recycler View
                mRecyclerView = findViewById(R.id.recyclerView);
                mAdapter = new CustomAdapter(MainActivity.this, heroes);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                // Adding a line divider between each item
                DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
                mRecyclerView.addItemDecoration(mDividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Some Error Occurred: Retry", Toast.LENGTH_SHORT ).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CustomAdapter.STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}