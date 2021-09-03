package com.learningandroid.childprotection.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.adapter.Parent_recycler_View_Adapter;
import com.learningandroid.childprotection.model.recycler_View_item_Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class parent_recycler_View_class extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<recycler_View_item_Class> userList;
    Parent_recycler_View_Adapter parentrecyclerViewAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recycler_view);
        searchView = findViewById(R.id.searchview);
        initData();
        initRecyclerView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                parentrecyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void initData() {
        //todo the backend code to get the realtime data will be written here
        userList = new ArrayList<>();

        // temprory list to check if the app is running
        //this is the list which is shown in the recycle view
        for(int i=0;i<5;i++) {
            userList.add(new recycler_View_item_Class(R.drawable.ap, "Amazon Prime", "04:23"));
            userList.add(new recycler_View_item_Class(R.drawable.fb, "Facebook", "05:03"));
            userList.add(new recycler_View_item_Class(R.drawable.fm, "Messenger", "06:20"));
            userList.add(new recycler_View_item_Class(R.drawable.hs, "HotStar", "01:12"));
            userList.add(new recycler_View_item_Class(R.drawable.ig, "Instagram", "01:56"));
            userList.add(new recycler_View_item_Class(R.drawable.nf, "Netflix", "00:54"));
            userList.add(new recycler_View_item_Class(R.drawable.pubg, "PUBG mobile", "05:21"));
            userList.add(new recycler_View_item_Class(R.drawable.sc, "Snapchat", "03:00"));
            userList.add(new recycler_View_item_Class(R.drawable.tg, "Telegram", "00:55"));
            userList.add(new recycler_View_item_Class(R.drawable.wa, "Whatsapp", "06:32"));
            userList.add(new recycler_View_item_Class(R.drawable.yt, "Youtube", "09:23"));
        }

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        parentrecyclerViewAdapter = new Parent_recycler_View_Adapter(userList);
        recyclerView.setAdapter(parentrecyclerViewAdapter);
        parentrecyclerViewAdapter.notifyDataSetChanged();
    }
}