package com.learningandroid.childprotection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learningandroid.childprotection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        btn = (Button) binding.ParentButton;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callrecyclerView();
            }
        });

    }


    public void callrecyclerView() {
        Intent intent = new Intent(this,recyclerView.class);
        startActivity(intent);
    }
}