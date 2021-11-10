package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.learningandroid.childprotection.Activities.Login.loginActivity;
import com.learningandroid.childprotection.R;

public class SplashScreen extends AppCompatActivity {

    private TextView heading;
    private LottieAnimationView anim;
    private Handler mWaitHandler;

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
                Intent i;
                if(false) i = new Intent(this, loginActivity.class);
                else     i= new Intent(this,statsView.class);
                startActivity(i);
                finish();
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
    }



}
