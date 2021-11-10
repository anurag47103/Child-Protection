package com.learningandroid.childprotection.Activities.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.learningandroid.childprotection.Activities.statsView;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.helper.OTP_Receiver;

public class otpActivity extends AppCompatActivity {

    private EditText otp;
    String verificationId;
    private OTP_Receiver otp_receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        verificationId = getIntent().getStringExtra("verificationId");
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
                Toast.makeText(otpActivity.this, "Timeout", Toast.LENGTH_SHORT).show();
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
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //otp successful
                                Toast.makeText(otpActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

                                callrecyclerView();
                            }
                            else{
                                otp.setError("Enter a valid 6 digit OTP");
                            }
                        }
                    });
        }
        else{
            otp.setError("Enter a valid 6 digit OTP");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(otp_receiver!=null) unregisterReceiver(otp_receiver);
    }

    public void callrecyclerView() {
        Intent intent = new Intent(this, statsView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }
}