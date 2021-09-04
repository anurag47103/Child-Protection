package com.learningandroid.childprotection;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public  class PermissionHandler extends AppCompatActivity {
    AppCompatActivity a;




    //below code for granting permission for location access
    public void getLocationAccess(AppCompatActivity a){
        this.a=a;
        if (ContextCompat.checkSelfPermission(a.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            Toast.makeText(a.getApplicationContext(),"Permission Granted for location",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(a.getApplicationContext(),"Permission Granted for location",Toast.LENGTH_LONG).show();
            }
        }
    }



}