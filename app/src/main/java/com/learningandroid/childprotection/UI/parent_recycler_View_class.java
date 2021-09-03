package com.learningandroid.childprotection.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.adapter.Parent_recycler_View_Adapter;
import com.learningandroid.childprotection.gettingStats.usage_Stats_Manager_Main;
import com.learningandroid.childprotection.model.recycler_View_item_Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class parent_recycler_View_class extends AppCompatActivity  {

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
        initData();//filling data
        initRecyclerView();// setting adapter
        preparesearchbar();//code for search bar


    }

    private void preparesearchbar() {
        //code for search bar
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
        //filling data
        userList = new ArrayList<>();
        usage_Stats_Manager_Main stats_manager = new usage_Stats_Manager_Main();
        userList = stats_manager.getuserlist();
    }

    private void initRecyclerView() {
        // setting adapter
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        parentrecyclerViewAdapter = new Parent_recycler_View_Adapter(userList);
        recyclerView.setAdapter(parentrecyclerViewAdapter);
        parentrecyclerViewAdapter.notifyDataSetChanged();
    }
}