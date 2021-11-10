package com.learningandroid.childprotection.Activities.newUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.commonUtils.commonUtil;

import java.util.ArrayList;
import java.util.List;

public class signup1 extends AppCompatActivity {

    private EditText[] names;
    private EditText[] phoneNUmbers;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        init();
        next.setOnClickListener(view -> nextPressed());
    }

    private void nextPressed() {

        String[] name = new String[5];
        String[] phone = new String[5];
        for(int i=0;i<5;i++){
            name[i]= names[i].getText().toString();
            phone[i]= phoneNUmbers[i].getText().toString().trim();
        }
//        if(name[0].trim().length()>0&&phone[0].length()==10&&name[2].trim().length()>0&&phone[2].length()==10)
        if(name[0].trim().length()>0&&phone[0].length()==10)
        {

            Intent i = new Intent(signup1.this,signup2.class);
            i.putExtra("names",name);
            i.putExtra("numbers",phone);
            startActivity(i);

        }
        else {
            if (name[0].trim().length() < 1) {
                names[0].setError("Enter name of parent 1");
            }
            if(phone[0].length()!=10){
                phoneNUmbers[0].setError("Enter 10 digit valid number of Parent 1");
            }
            if (name[2].trim().length() < 1) {
                names[2].setError("Enter name of parent 1");
            }
            if(phone[2].length()!=10){
                phoneNUmbers[2].setError("Enter 10 digit valid number of Parent 1");
            }
            Toast.makeText(signup1.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
        }





    }

    private void init(){
        names = new EditText[5];
        phoneNUmbers = new EditText[5];
        names[0]= findViewById(R.id.parentName1);
        names[1]= findViewById(R.id.parentName2);
        names[2]= findViewById(R.id.childName1);
        names[3]= findViewById(R.id.childName2);
        names[4]= findViewById(R.id.childName3);
        phoneNUmbers[0]= findViewById(R.id.parentNumber1);
        phoneNUmbers[1]= findViewById(R.id.parentNumber2);
        phoneNUmbers[2]= findViewById(R.id.childNumber1);
        phoneNUmbers[3]= findViewById(R.id.childNumber2);
        phoneNUmbers[4]= findViewById(R.id.childNumber3);
        next = findViewById(R.id.next);
    }
}