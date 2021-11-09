package com.learningandroid.childprotection.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.learningandroid.childprotection.R;

import java.util.concurrent.TimeUnit;

public class loginActivity extends AppCompatActivity {

    private EditText phone;
    private FirebaseAuth mAuth;
    private String TAG ="Tag";
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        phone = findViewById(R.id.phonenumber);
        mAuth = FirebaseAuth.getInstance();
        
        findViewById(R.id.getotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                String s = phone.getText().toString();
                if(s.length()==10){
                    sendOtp();
                }else Toast.makeText(loginActivity.this, "Enter valid number", Toast.LENGTH_SHORT).show();
            }
        });
        

    }

    private void sendOtp() {


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d(TAG,e.toString());
                Toast.makeText(loginActivity.this, "Enter Number Again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Intent i = new Intent(loginActivity.this,otpActivity.class);
                i.putExtra("phone",phone.getText().toString());
                i.putExtra("verificationId",verificationId);
                startActivity(i);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phone.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }


}