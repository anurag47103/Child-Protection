package com.learningandroid.childprotection.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.learningandroid.childprotection.R;

public class loginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        findViewById(R.id.ParentButton).setOnClickListener(View -> callrecyclerView());

    }

    // this method is called when the parent button is pressed
    public void callrecyclerView() {
        Intent intent = new Intent(this, statsView.class);
        startActivity(intent);
        finish();
    }
}