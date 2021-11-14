package com.learningandroid.childprotection.Activities.newUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learningandroid.childprotection.Activities.Login.loginActivity;
import com.learningandroid.childprotection.Activities.Login.otpActivity;
import com.learningandroid.childprotection.Activities.SplashScreen;
import com.learningandroid.childprotection.Activities.statsView;
import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.model.userDetails;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class signup2 extends AppCompatActivity {

    private TextView[] OTPfor;
    private String[] names;
    private  String[] phones;
    private boolean[] verified;
    private EditText[] otpArray;
    private RelativeLayout[] relArray;
    private String[] verificationidArray;
    public int index=0;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private Button btn;
    private DocumentReference mDocRef;
    private final String tag ="Upload Results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);


        init();
        otpVerification();
        btn.setOnClickListener(view -> verifynumber());

        call();
    }

    private void verifynumber() {
        String s = otpArray[index].getText().toString().trim();
        String v = verificationidArray[index];

        if(s.length()==6&&v!=null){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(v,s);
            FirebaseAuth
                    .getInstance()
                    .signInWithCredential(credential)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            //otp successful
                            verified[index]=true;
                            otpArray[index].setText(names[index]+"is Verified");
                            OTPfor[index].setText("Phone: +91 "+phones[index]);
                            otpArray[index].setFocusable(false);
                            if(index<4){
                                btn.setVisibility(View.GONE);
                                index++;
                                otpVerification();
                            }else{call();}
                        }
                        else{
                            otpArray[index].setError("Invalid OTP");
                        }
                    });
        }
        else{
            otpArray[index].setError("Enter a valid 6 digit OTP");
        }
    }

    private void otpVerification() {

       relArray[index].animate().alpha(1).setDuration(500);
       if(index==0)
//       if(index==0||index==2)
            sendOtp(phones[index]);
       else{
           if(phones[index].trim().length()!=10){
               OTPfor[index].setText("Verification failed for "+phones[index]);
               verified[index]=false;
               index++;
               if(index<5) otpVerification();
               else call();
           }
           else{
               sendOtp(phones[index]);
           }

       }


    }

    private void sendOtp(String phonenumber) {


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
//                if(index==0||index==2)
                if(index==0)
                {
                    Log.d("OTP",e.toString());
                    Toast.makeText(signup2.this, "Error sending OTP try again later", Toast.LENGTH_LONG).show();
//                    finish();
                }
                else{
                    verified[index]=false;
                    if(index<4) {
                        OTPfor[index].setText("Verification failed for "+phones[index]);
                        index++;
                        otpVerification();
                    }
                    else{call();}
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(signup2.this, "Code sent to "+phones[index], Toast.LENGTH_SHORT).show();
                verificationidArray[index]=verificationId;
                btn.setVisibility(View.VISIBLE);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(("+91"+phonenumber).trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void call() {
        // all otp verified
        // numbers with names stored now save it to database

        Log.d(tag,"Call method called");




        Random rnd = new Random();
        String grpId=phones[0]+"."+java.time.Clock.systemUTC().instant().toString().trim();
        HashMap<String , String> data = new HashMap<>();
        for(int i=0;i<5;i++){
            Character r;  if(i==0||i==1)r='p';  else r='c';
            userDetails temp = new userDetails(names[i],"+91"+phones[i],r,grpId,null);
            if(verified[i])
                data.put("+91"+phones[i],temp.toString());
        }

        mDocRef = FirebaseFirestore.getInstance().document("Home/Contacts");
        mDocRef.set(data).addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Log.d(tag,"Upload Failed", task.getException());
//                startActivity(new Intent(this, SplashScreen.class));
            }else {
                Log.d(tag,"Upload Successful");
                createGroup(grpId);
            }
        });
    }

    private void createGroup( String grpId) {

        HashMap<String, String> grp = new HashMap<>();
        String con = "{[";
        for(int i=0;i<5;i++){
            if(verified[i]) con+=phones[i]+",";
        }
        con = con.substring(0,con.length()-1);
        con+="]}";
        grp.put(grpId,con);
        mDocRef = FirebaseFirestore.getInstance().document("Home/Groups");
        mDocRef.set(grp).addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Log.d(tag,"Upload Failed", task.getException());
//                startActivity(new Intent(this, SplashScreen.class));
            }else {
                Log.d(tag,"Upload Successful");
//                Intent i = new Intent(signup2.this, statsView.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);

            }
        });
    }

    private void init(){
        names = getIntent().getStringArrayExtra("names");
        phones = getIntent().getStringArrayExtra("numbers");
        OTPfor = new TextView[5];

        OTPfor[0]= findViewById(R.id.otp1);
        OTPfor[1]= findViewById(R.id.otp2);
        OTPfor[2]= findViewById(R.id.otp3);
        OTPfor[3]= findViewById(R.id.otp4);
        OTPfor[4]= findViewById(R.id.otp5);
        for(int i=0;i<5;i++){
            if(phones[i].trim().length()==10)
                OTPfor[i].setText("OTP for "+names[i]+": +91 "+phones[i]);
            else OTPfor[i].setText("No or invalid entry");

        }
        otpArray = new EditText[5];
        otpArray[0]= findViewById(R.id.enterotp1);
        otpArray[1]= findViewById(R.id.enterotp2);
        otpArray[2]= findViewById(R.id.enterotp3);
        otpArray[3]= findViewById(R.id.enterotp4);
        otpArray[4]= findViewById(R.id.enterotp5);


        relArray = new RelativeLayout[5];
        relArray[0]=findViewById(R.id.rel1);
        relArray[1]=findViewById(R.id.rel2);
        relArray[2]=findViewById(R.id.rel3);
        relArray[3]=findViewById(R.id.rel4);
        relArray[4]=findViewById(R.id.rel5);
        for(int i=0;i<5;i++) relArray[i].setAlpha(0);
        btn = findViewById(R.id.btn);
        btn.setVisibility(View.GONE);

        verificationidArray= new String[5];
        mAuth = FirebaseAuth.getInstance();

        verified= new boolean[5];



    }
}























