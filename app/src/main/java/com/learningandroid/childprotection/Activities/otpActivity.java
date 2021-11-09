package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.learningandroid.childprotection.R;

public class otpActivity extends AppCompatActivity {

    private EditText otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp = findViewById(R.id.otp);
        findViewById(R.id.verify).setOnClickListener(view -> {
            if(verify()){

            }else {
                Toast.makeText(this, "Please enter valid otp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verify() {

        return true;
    }


    public void callrecyclerView() {
        Intent intent = new Intent(this, statsView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}