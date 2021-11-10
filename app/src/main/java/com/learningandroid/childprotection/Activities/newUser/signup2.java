package com.learningandroid.childprotection.Activities.newUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.commonUtils.commonUtil;

import java.util.ArrayList;
import java.util.List;

public class signup2 extends AppCompatActivity {

    private int noOfChildren;
    private List<String> names;
    private List<String> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);


        init();



    }
    private void init(){
        names = new ArrayList<>();
        numbers = new ArrayList<>();
        names.addAll(commonUtil.newreg.getName());
        numbers.addAll(commonUtil.newreg.getNumber());
        noOfChildren = getIntent().getIntExtra("childcount",0);
    }
}