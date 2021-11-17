package com.learningandroid.childprotection.Activities.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learningandroid.childprotection.Activities.SplashScreen;
import com.learningandroid.childprotection.Activities.newUser.signup1;
import com.learningandroid.childprotection.Activities.statsView;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.commonUtils.commonUtil;
import com.learningandroid.childprotection.helper.OTP_Receiver;

import java.util.HashMap;

public class otpActivity extends AppCompatActivity {

    private EditText otp;
    private String verificationId,phone;
    private OTP_Receiver otp_receiver;
    private DocumentReference mDocRef;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        phone = "+91"+getIntent().getStringExtra("phone");

        verificationId = getIntent().getStringExtra("verificationId");
        mDocRef = FirebaseFirestore.getInstance().document("Groups/"+phone);
        otp = findViewById(R.id.otp);
        findViewById(R.id.verify).setOnClickListener(view -> {
           verify();
        });

        autoOtpreciever();
    }

    private void autoOtpreciever(){
        otp_receiver = new OTP_Receiver();
        this.registerReceiver(otp_receiver,new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
        otp_receiver.initListener(new OTP_Receiver.OtpreceiverListner() {
            @Override
            public void onOtpSuccess(String otps) {
                //put otp in edit text view
                otp.setText(otps);
            }

            @Override
            public void onOtptimeout() {
                Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verify() {
        String s = otp.getText().toString();
        if(s.length()==6&&verificationId!=null){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,s);
            FirebaseAuth
                    .getInstance()
                    .signInWithCredential(credential)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            //otp successful
                            Toast.makeText(context, "Otp Verified", Toast.LENGTH_SHORT).show();
                            SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
                            preferences.edit().putString("phone", phone).apply();
                            checkInDatabase();


                        }
                        else{
                            otp.setError("Enter a valid 6 digit OTP");
                        }
                    });
        }
        else{
            otp.setError("Enter a valid 6 digit OTP");
        }
    }

    private void checkInDatabase() {
        mDocRef.get().addOnSuccessListener(documentSnapshot -> {
            //todo user exists
            Intent i =new Intent(context,statsView.class);
            commonUtil.phone = phone;//todo error
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }).addOnFailureListener(e -> {
            //todo error
            Intent i =new Intent(context,signup1.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(otp_receiver!=null) unregisterReceiver(otp_receiver);
    }

}