package com.learningandroid.childprotection.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.adapter.statsAdapter;
import com.learningandroid.childprotection.commonUtils.commonUtil;
import com.learningandroid.childprotection.model.statsItem;
import com.learningandroid.childprotection.model.userDetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class statsView extends AppCompatActivity {

    private UsageStatsManager usageStatsManager;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<statsItem> userList;
    private statsAdapter parentrecyclerViewAdapter;
    private SearchView searchView;
    private FloatingActionButton fab;
    private FusedLocationProviderClient fusedLocationClient;
    private DocumentReference mDocRef;
    private Context context = this;
    private HashMap grpMap;
    private final String tag = "StatsView";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        init();
        if (!checkUserStatsPermission()) {

            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.alertdialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            dialog.show();
            dialog.setOnDismissListener(dialogInterface -> {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                finish();
            });
            dialog.findViewById(R.id.dialog).setOnClickListener(view -> {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                finish();
            });

        } else {
//            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            parentrecyclerViewAdapter = new statsAdapter(userList);
            recyclerView.setAdapter(parentrecyclerViewAdapter);
            parentrecyclerViewAdapter.notifyDataSetChanged();


            preparesearchbar();//code for search bar
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lookforLocationPermission();

                }
            });

        }
        getFromDatabase();



    }

    private void getGroupInfo() {
        mDocRef = FirebaseFirestore.getInstance().document("Groups/"+commonUtil.MyData.getGroupId());
        mDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()) {
                grpMap = (HashMap) documentSnapshot.getData();
                setGrpInfo();
            }else {
                //todo error
            }

        }).addOnFailureListener(e -> Toast.makeText(context, "Unable to fetch data, Try again later", Toast.LENGTH_SHORT).show());


    }

    private void setGrpInfo() {
        for(int i=0;i<5;i++){

            if(grpMap.containsKey(i+"")){
                mDocRef = FirebaseFirestore.getInstance().document("Users/"+grpMap.get(i+""));
                System.out.println("path: "+grpMap.get(i+""));
                int finalI = i;
                mDocRef.get().addOnSuccessListener(documentSnapshot -> {

                    String s =documentSnapshot.toObject(userDetails.class).toString();
                    System.out.println(s);
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, "Unable to fetch grp data, Try again later", Toast.LENGTH_SHORT).show();

                });

            }
        }
    }

    private void getFromDatabase() {
        mDocRef = FirebaseFirestore.getInstance().document("Users/"+commonUtil.phone);
        mDocRef.get().addOnSuccessListener(documentSnapshot -> {
            commonUtil.MyData  = documentSnapshot.toObject(userDetails.class);

            getGroupInfo();


        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Unable to fetch data, Try again later", Toast.LENGTH_SHORT).show();
        });

    }


    private boolean checkUserStatsPermission() {


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        String s1 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.getTimeInMillis(), System.currentTimeMillis()) + "";
//        System.out.println(s1);
        if (s1.length() > 2) {

//                Log.d("check", "onCreate: " + usageStatsManager.getAppStandbyBucket());
            List<UsageStats> queryUsageStats = usageStatsManager
                    .queryUsageStats(
                            UsageStatsManager.INTERVAL_DAILY,
                            cal.getTimeInMillis(),
                            System.currentTimeMillis()
                    );

//                Log.d("check", cal.getTimeInMillis() + "  onCreate: hogaya" + System.currentTimeMillis());
            userList = new ArrayList<>();
            for (UsageStats x : queryUsageStats) {
                long tt = x.getTotalTimeInForeground();


                String s = x.getPackageName();
                if (commonUtil.applist.containsKey(s)) {
                    Long time = commonUtil.applist.get(s) + tt;
                    commonUtil.applist.put(s, time);
                } else commonUtil.applist.put(s, 0L);

            }
            for (Map.Entry<String, Long> entry : commonUtil.applist.entrySet()) {

                String name = entry.getKey();
                Long tt = entry.getValue();
                long min = TimeUnit.MILLISECONDS.toMinutes(tt) % 60;
                long hrs = TimeUnit.MILLISECONDS.toHours(tt);

                String time = hrs + ":" + min;
                if (hrs > 0 || min > 10)
                    userList.add(new statsItem(R.drawable.ap, name, time + ""));
            }
            return true;
        }
        return false;
    }



    private void lookforLocationPermission() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    commonUtil.location = location;
                    commonUtil.MyData.setLocation(location);
                    Log.d(tag,location.toString());
                }
                launchmap();
            });
        } else {
            Toast.makeText(this, "Please give location permissions to use the button.", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(statsView.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }
    }

    private void init() {
        searchView = findViewById(R.id.searchview);
        fab = findViewById(R.id.mapbutton);
        recyclerView = findViewById(R.id.recyclerview);
        usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        grpMap = new HashMap<String,Object>();

    }

    private void launchmap() {
        //launch map
        if (commonUtil.location != null) {
            String latitude = commonUtil.location.getLatitude() + "";
            String longitude = commonUtil.location.getLongitude() + "";
            Uri uri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } else {
            Toast.makeText(context, "Wait for app to load map try in a few seconds.", Toast.LENGTH_SHORT).show();
        }

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


}