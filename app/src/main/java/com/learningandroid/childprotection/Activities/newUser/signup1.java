package com.learningandroid.childprotection.Activities.newUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.commonUtils.commonUtil;

import java.util.ArrayList;
import java.util.List;

public class signup1 extends AppCompatActivity {

    private EditText p1,f1,p2,f2,noOfC;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        init();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPressed();
            }
        });
    }

    private void nextPressed() {
        String sp1=p1.getText().toString();
        String sp2=p2.getText().toString();
        String sf1=f1.getText().toString().trim();
        String sf2=f2.getText().toString().trim();

        if(sp1.trim().length()>0&&sf1.length()==10&&noOfC.getText().toString().trim().length()>0){
            int NOc=Integer.parseInt(noOfC.getText().toString().trim());
            Log.d("TAG","Required field entered successfully");

            List<String> names = new ArrayList<>();
            List<String> phone = new ArrayList<>();
            names.add(sp1);
            phone.add(sf1);
            if(sp2.trim().length()>0&&sf2.length()==10){
                names.add(sp2);
                phone.add(sf2);
            }
            else{
                names.add(null);
                phone.add(null);
            }


            commonUtil.newreg.setName(names);
            commonUtil.newreg.setNumber(phone);

            Intent intent = new Intent(this,signup2.class);
            intent.putExtra("childcount",NOc);
            startActivity(intent);




        }
        else {
            if (sp1.trim().length() < 1) {
                p1.setError("Enter name of parent 1");
            }
            if(sf1.length()!=10){
                f1.setError("Enter 10 digit valid number of Parent 1");
            }
            if(noOfC.getText().toString().trim().length()<1){
                noOfC.setError("Enter the number of child/children");
            }
        }





    }

    private void init(){
        p1= findViewById(R.id.parentName1);
        p2= findViewById(R.id.parentName2);
        f1= findViewById(R.id.parentNumber1);
        f2= findViewById(R.id.parentNumber2);
        noOfC= findViewById(R.id.noOfChildren);
        next = findViewById(R.id.next);
    }
}