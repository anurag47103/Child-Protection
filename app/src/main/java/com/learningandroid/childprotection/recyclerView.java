package com.learningandroid.childprotection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class recyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recycler_view);
        initData();
        initRecyclerView();
    }

    private void initData() {
        //todo the backend code to get the realtime data will be written here
        userList = new ArrayList<>();

        // temprory list to check if the app is running
        //this is the list which is shown in the recycle view
        userList.add(new ModelClass(R.drawable.ap,"Amazon Prime","04:23"));
        userList.add(new ModelClass(R.drawable.fb,"Facebook","05:03"));
        userList.add(new ModelClass(R.drawable.fm,"Messenger","06:20"));
        userList.add(new ModelClass(R.drawable.hs,"HotStar","01:12"));
        userList.add(new ModelClass(R.drawable.ig,"Instagram","01:56"));
        userList.add(new ModelClass(R.drawable.nf,"Netflix","00:54"));
        userList.add(new ModelClass(R.drawable.pubg,"PUBG mobile","05:21"));
        userList.add(new ModelClass(R.drawable.sc,"Snapchat","03:00"));
        userList.add(new ModelClass(R.drawable.tg,"Telegram","00:55"));
        userList.add(new ModelClass(R.drawable.wa,"Whatsapp","06:32"));
        userList.add(new ModelClass(R.drawable.yt,"Youtube","09:23"));
        userList.add(new ModelClass(R.drawable.ap,"Amazon Prime","04:23"));
        userList.add(new ModelClass(R.drawable.fb,"Facebook","05:03"));
        userList.add(new ModelClass(R.drawable.fm,"Messenger","06:20"));
        userList.add(new ModelClass(R.drawable.hs,"HotStar","01:12"));
        userList.add(new ModelClass(R.drawable.ig,"Instagram","01:56"));
        userList.add(new ModelClass(R.drawable.nf,"Netflix","00:54"));
        userList.add(new ModelClass(R.drawable.pubg,"PUBG mobile","05:21"));
        userList.add(new ModelClass(R.drawable.sc,"Snapchat","03:00"));
        userList.add(new ModelClass(R.drawable.tg,"Telegram","00:55"));
        userList.add(new ModelClass(R.drawable.wa,"Whatsapp","06:32"));
        userList.add(new ModelClass(R.drawable.yt,"Youtube","09:23"));



    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}