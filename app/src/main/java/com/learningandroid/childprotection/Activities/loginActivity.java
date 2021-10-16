package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.databinding.ActivityMainBinding;

public class loginActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
/*
* This is the home screen here we are having two buttons 1 Child 2 parent
* On parent button clicked we are going to the new activity where we are having the
* recycler view;
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(loginActivity.this, R.layout.activity_main);

        binding.ParentButton.setOnClickListener(view -> callrecyclerView());

    }

    // this method is called when the parent button is pressed
    public void callrecyclerView() {
        Intent intent = new Intent(this, parentRecyclerView.class);
        startActivity(intent);
        Log.i("New Activity opened","parent_recycler_View_class opened");
    }
}