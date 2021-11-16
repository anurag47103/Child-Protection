package com.learningandroid.childprotection.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
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

public class statsView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private androidx.appcompat.widget.Toolbar toolbar;
    private TextView title;
    private ProgressBar progressBar;
    private userDetails dispData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        init();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);




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

        }
        else {
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

        getFromDatabase(commonUtil.phone);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //navigation Item selected

        switch (item.getItemId()){
            case R.id.profile:

                drawerLayout.closeDrawers();
                break;


            case R.id.p1:
//                System.out.println("p1");
                if(grpMap.get("0")!=null)
                    getFromDatabase(grpMap.get("0").toString());
                else {
                    Log.d(tag,"No user");
                    Toast.makeText(context, "No user", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                break;


            case R.id.p2:
//                System.out.println("p2");
                if(grpMap.get("1")!=null)
                    getFromDatabase(grpMap.get("1").toString());
                else {
                    Log.d(tag,"No user");
                    Toast.makeText(context, "No user", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                break;


            case R.id.c1:
//                System.out.println("c1");
                if(grpMap.get("2")!=null)
                    getFromDatabase(grpMap.get("2").toString());
                else {
                    Log.d(tag,"No user");
                    Toast.makeText(context, "No user", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                break;


            case R.id.c2:
//                System.out.println("c2");
                if(grpMap.get("3")!=null)
                    getFromDatabase(grpMap.get("3").toString());
                else {
                    Log.d(tag,"No user");
                    Toast.makeText(context, "No user", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                break;

            case R.id.c3:
//                System.out.println("c3");
                if(grpMap.get("4")!=null)
                    getFromDatabase(grpMap.get("4").toString());
                else {
                    Log.d(tag,"No user");
                    Toast.makeText(context, "No user", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                break;


            case R.id.logout:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
                preferences.edit().putString("phone", "").apply();
                Intent i = new Intent(context,SplashScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                drawerLayout.closeDrawers();
                break;
        }

        return true;
    }

    private void init() {

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchview);
        fab = findViewById(R.id.mapbutton);
        recyclerView = findViewById(R.id.recyclerview);
        usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        grpMap = new HashMap<String,Object>();
        title = findViewById(R.id.title_name);
        progressBar = findViewById(R.id.progressbar);
        dispData = new userDetails();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void getFromDatabase(String phone) {
        progressBar.setVisibility(View.VISIBLE);

        mDocRef = FirebaseFirestore.getInstance().document("Users/"+phone);
        mDocRef.get().addOnSuccessListener(documentSnapshot -> {
            dispData  = documentSnapshot.toObject(userDetails.class);
            title.setText(dispData.getName());

            getGroupInfo();


        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Unable to fetch data, Try again later", Toast.LENGTH_SHORT).show();
        });



    }

    private void getGroupInfo() {
        mDocRef = FirebaseFirestore.getInstance().document("Groups/"+dispData.getGroupId());
        mDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()) {
                grpMap = (HashMap) documentSnapshot.getData();
                Log.d(tag,"Group Info: "+grpMap.toString());

                
                progressBar.setVisibility(View.GONE);


            }else {
                //todo error
                Log.d(tag, "getGroupInfo: null");
                Toast.makeText(context, "Unable to fetch data", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(e -> Toast.makeText(context, "Unable to fetch data, Try again later", Toast.LENGTH_SHORT).show());


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