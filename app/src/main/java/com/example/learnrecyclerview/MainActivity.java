package com.example.learnrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

class Info{
    private String name;
    private String dob;
    private String address;

    public Info(){
        name = "NAME";
        dob = "DOB";
        address = "ADDRESS";
    }

    public Info(String Name, String Dob, String Address){
        name = Name;
        dob = Dob;
        address = Address;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class MainActivity extends AppCompatActivity {

    private  ArrayList<Info> infoList = new ArrayList<Info>();
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 1; i <= 20; i++) {
            Info info = new Info();
            info.setName("NAME" + i);
            info.setDob("DOB"+i);
            info.setAddress("ADDRESS"+i);
            infoList.add(info);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new CustomAdapter(this, infoList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
    }
}