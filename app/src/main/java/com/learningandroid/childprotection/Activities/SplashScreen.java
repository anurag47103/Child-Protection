package com.learningandroid.childprotection.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learningandroid.childprotection.Activities.Login.loginActivity;
import com.learningandroid.childprotection.Activities.newUser.signup1;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.commonUtils.commonUtil;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    private TextView heading;
    private LottieAnimationView anim;
    private Handler mWaitHandler;
    private DocumentReference mDocRef;
    private String tag = "splash";
    private SharedPreferences preferences;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);


        init();
        animate();

        mWaitHandler.postDelayed(()->{
            try{
                heading.animate().alpha(0).setDuration(500);

//                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
//                preferences.edit().putString("phone", "+916203310325").apply();

                commonUtil.phone =  preferences.getString("phone", "");
                if(commonUtil.phone.length()>0){
                    mDocRef.get().addOnSuccessListener(documentSnapshot -> {

                        startActivity(new Intent(context,statsView.class));
                        finish();

                    }).addOnFailureListener(e -> {
                        startActivity(new Intent(context,signup1.class));
                        finish();
                    });
                }
                else{
                   startActivity(new Intent(context,loginActivity.class));
                   finish();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        },4000);


    }


    private void animate(){
        heading.animate()
                .scaleX(2)
                .scaleY(2)
                .alpha(1)
                .setDuration(1000);
        anim.animate()
                .alpha(0)
                .setDuration(500)
                .setStartDelay(4000);
    }

    private void init(){
        heading = findViewById(R.id.heading);
        heading.setAlpha(0);
        anim = findViewById(R.id.splashAnim);
        mWaitHandler = new Handler();
        mDocRef = FirebaseFirestore.getInstance().document("Home/"+commonUtil.phone);
        preferences = getApplicationContext().getSharedPreferences("MY_APP", MODE_PRIVATE);

    }



}
