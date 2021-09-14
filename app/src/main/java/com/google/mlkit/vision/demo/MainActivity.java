package com.google.mlkit.vision.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private Button getOTP;
    private Button submitOTP;
    private String otp;
    private EditText otpInput;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        otp = "";
        getOTP = findViewById(R.id.getOTP);
        submitOTP = findViewById(R.id.submitOTP);
        otpInput = findViewById(R.id.otpInput);

        getOTP.setOnClickListener(v -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            String rand = Integer.toString(random.nextInt(1,1000000));
            if(rand.length() < 6) {
                int left = 6 - rand.length();
                for(int i=0;i<left;i++) {
                    rand = '0' + rand;
                }
            }
            otp = rand;
            Toast.makeText(this, "Testing OTP: "+otp, Toast.LENGTH_SHORT).show();
        });

        submitOTP.setOnClickListener(v -> {
            String enteredOtp = otpInput.getText().toString();
            if(otp=="") {
                Toast.makeText(this, "Please request an OTP", Toast.LENGTH_SHORT).show();
                return;
            }
            if(otp.equals(enteredOtp)) {
                startActivity(new Intent(this, EntryChoiceActivity.class));
            }
            else {
                Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                return;
            }
        });


    }
}