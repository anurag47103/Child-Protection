package com.learningandroid.childprotection.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    LocationManager locationManager;
    LocationListener locationListener;
    FloatingActionButton morebtn,navigationbtn,locationbtn,camerabtn;
    String Location;
    Animation rotateforward,ratatebackward,fabclose,fabopen;
    boolean isoOpen = false;

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

        initialise();

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

        initData();//filling data
        initRecyclerView();// setting adapter
        preparesearchbar();//code for search bar
        fabonclick();




    }

    private void fabonclick() {
        morebtn.setOnClickListener(new View.OnClickListener() {
            //Floating Action Button
            @Override
            public void onClick(View view) {
                //fab button clicked
                Log.i("FAB ","Clicked");
                if(!isoOpen){
                    isoOpen=true;
                    morebtn.startAnimation(rotateforward);
                    navigationbtn.startAnimation(fabopen);
                    locationbtn.startAnimation(fabopen);
                    camerabtn.startAnimation(fabopen);
                }else {
                    isoOpen=false;
                    morebtn.startAnimation(ratatebackward);
                    navigationbtn.startAnimation(fabclose);
                    locationbtn.startAnimation(fabclose);
                    camerabtn.startAnimation(fabclose);
                }
            }
        });

        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchmap();
            }
        });



    }

    private void initialise() {
        searchView = findViewById(R.id.searchview);
        morebtn = findViewById(R.id.morebtn);
        navigationbtn =findViewById(R.id.navigationbtn);
        locationbtn = findViewById(R.id.locationbtn);
        camerabtn = findViewById(R.id.camerabtn);
        rotateforward = AnimationUtils.loadAnimation(this,R.anim.rotate_btn_anim);
        ratatebackward =AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim);
        fabclose=AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim);
        fabopen=AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim);



    }

    private void launchmap() {
        //launch map
        Uri uri = Uri.parse("geo:0,0?q=12.8988756,77.5218414,17z");
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