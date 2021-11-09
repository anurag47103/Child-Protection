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

    private EditText phone,otp;
    private Button signin;
    private Boolean getOtp;
    private Editable phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        init();
        signin.setText("Get Otp");
        otp.setAlpha(0);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getOtp){
                    phoneNumber= phone.getText();
                    if(phoneNumber.toString().length()>9) {
                        getOtp = false;
                        signin.setText("Submit");
                        otp.animate().alpha(1).setDuration(500);
                        phone.setActivated(false);
                        getotp(phoneNumber.toString());
                    }
                    else Toast.makeText(loginActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                }else{
                    //authanticate
                    callrecyclerView();
                }
            }
        });

    }

    private void getotp(String s){

    }

    private void init(){
        phone = findViewById(R.id.phonenumber);
        otp = findViewById(R.id.otp);
        signin = findViewById(R.id.ParentButton);
        getOtp = true;

    }

    // this method is called when the parent button is pressed
    public void callrecyclerView() {
        Intent intent = new Intent(this, statsView.class);
        startActivity(intent);
        finish();
    }
}