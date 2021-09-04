package com.learningandroid.childprotection.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.learningandroid.childprotection.PermissionHandler;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.adapter.parentRecyclerViewAdapter;
import com.learningandroid.childprotection.gettingStats.usage_Stats_Manager_Main;
import com.learningandroid.childprotection.model.recyclerViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class parentRecyclerView extends AppCompatActivity  {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<recyclerViewItem> userList;
    parentRecyclerViewAdapter parentrecyclerViewAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recycler_view);
        PermissionHandler p = new PermissionHandler();
        p.getLocationAccess(this);
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
        parentrecyclerViewAdapter = new parentRecyclerViewAdapter(userList);
        recyclerView.setAdapter(parentrecyclerViewAdapter);
        parentrecyclerViewAdapter.notifyDataSetChanged();
    }
}