package com.voting.mlkit.vision.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.mlkit.vision.demo.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private Button getOTP;
    private Button submitOTP;
    private String otp;
    private EditText otpInput;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        otp = "";
        getOTP = findViewById(R.id.getOTP);
        submitOTP = findViewById(R.id.submitOTP);
        otpInput = findViewById(R.id.otpInput);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode,
                                                      @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        String rand = Integer.toString(random.nextInt(1,1000000));
                        if(rand.length() < 6) {
                            int left = 6 - rand.length();
                            for(int i=0;i<left;i++) {
                                rand = '0' + rand;
                            }
                        }
                        otp = rand;
                        Toast.makeText(getApplicationContext(), "Testing OTP: "+otp, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for the voting system")
                .setSubtitle("Log in using your fingerprint")
                .setNegativeButtonText("Cancel")
                .build();
        getOTP.setOnClickListener(v -> {
            biometricPrompt.authenticate(promptInfo);
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