package com.google.mlkit.vision.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class SuccessScreen extends AppCompatActivity {
    private Button closeButton;
    private String candidate;
    private String area;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v-> {
            finishAndRemoveTask();
            finishAffinity();
            System.exit(0);
        });
     Bundle extras = getIntent().getExtras();
     candidate = extras.getString("Candidate");
     area = extras.getString("Area");
    }
    @Override
    public void onBackPressed() {
        //Prevents back button from being pressed
    }
}