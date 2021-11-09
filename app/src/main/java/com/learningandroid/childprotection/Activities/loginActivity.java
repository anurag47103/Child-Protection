package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.learningandroid.childprotection.R;

import java.util.concurrent.TimeUnit;

public class loginActivity extends AppCompatActivity {

    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        phone = findViewById(R.id.phonenumber);
        findViewById(R.id.otp).setOnClickListener(view -> {
           String s = phone.getText().toString();
           if(s.length()==9){
               Intent i = new Intent(loginActivity.this,otpActivity.class);
               startActivity(i);
           }else Toast.makeText(this,"Please enter a valid phone no.", Toast.LENGTH_SHORT).show();
        });

    }
}