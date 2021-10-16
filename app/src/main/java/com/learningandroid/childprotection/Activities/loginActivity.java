package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.learningandroid.childprotection.R;

public class loginActivity extends AppCompatActivity {

/*
* This is the home screen here we are having two buttons 1 Child 2 parent
* On parent button clicked we are going to the new activity where we are having the
* recycler view;
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        findViewById(R.id.ParentButton).setOnClickListener(View ->{
            callrecyclerView();
        });


    }

    // this method is called when the parent button is pressed
    public void callrecyclerView() {
        Intent intent = new Intent(this, statsView.class);
        startActivity(intent);
        finish();
    }
}