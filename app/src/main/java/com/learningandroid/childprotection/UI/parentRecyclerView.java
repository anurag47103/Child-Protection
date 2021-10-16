package com.learningandroid.childprotection.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.adapter.parentRecyclerViewAdapter;
import com.learningandroid.childprotection.gettingStats.usage_Stats_Manager_Main;
import com.learningandroid.childprotection.model.recyclerViewItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class parentRecyclerView extends AppCompatActivity  {

    UsageStatsManager usageStatsManager;
    parentRecyclerViewAdapter parentRecyclerViewAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<recyclerViewItem> userList;
    parentRecyclerViewAdapter parentrecyclerViewAdapter;
    SearchView searchView;
    LocationManager locationManager;
    LocationListener locationListener;
    FloatingActionButton fab;
    String Location;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recycler_view);

//        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
//        startActivity(intent);


        usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        Log.d("check", "onCreate: " + usageStatsManager.getAppStandbyBucket());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        List<UsageStats> queryUsageStats = usageStatsManager
                .queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.getTimeInMillis(),
                        System.currentTimeMillis());

        Log.d("check", cal.getTimeInMillis() + "  onCreate: hogaya" + System.currentTimeMillis());
        userList = new ArrayList<>();
        for(UsageStats x : queryUsageStats) {
                long tt = x.getTotalTimeInForeground();
                long min = TimeUnit.MILLISECONDS.toMinutes(tt);
                long hrs = TimeUnit.MILLISECONDS.toHours(tt);

                String time = hrs + ":" + min;
                if(min > 10) {
                    userList.add(new recyclerViewItem(R.drawable.ap, x.getPackageName(),time));
                    Log.d("check", x.getTotalTimeInForeground() +  "usageStats   " + x.getPackageName());
                }


        }

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        parentrecyclerViewAdapter = new parentRecyclerViewAdapter(userList);
        recyclerView.setAdapter(parentrecyclerViewAdapter);
        parentrecyclerViewAdapter.notifyDataSetChanged();



        searchView = findViewById(R.id.searchview);
        fab = findViewById(R.id.mapbutton);

        //location permission
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.i("Location ",location.toString());
                Location=location.toString();

            }
        };
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

//        initData();//filling data
//        initRecyclerView();// setting adapter
        preparesearchbar();//code for search bar
        fab.setOnClickListener(new View.OnClickListener() {
            //Floating Action Button
            @Override
            public void onClick(View view) {
                //fab button clicked
                Log.i("FAB ","Clicked");
                launchmap();

            }
        });//Floating Action Button



    }

    private void launchmap() {
        //launch map
        Uri uri = Uri.parse("geo:0,0?q=12.8999689,77.5225605");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
        mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

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