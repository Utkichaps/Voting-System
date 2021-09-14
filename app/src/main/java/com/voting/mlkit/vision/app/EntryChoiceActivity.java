/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.voting.mlkit.vision.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.demo.R;
import com.voting.mlkit.vision.app.java.CameraXLivePreviewActivity;

public class EntryChoiceActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_entry_choice);

        findViewById(R.id.java_entry_point).setOnClickListener(v -> {
            Class faceDetection = CameraXLivePreviewActivity.class;
            startActivity(new Intent(this, faceDetection));
        });
    }

    @Override
    public void onBackPressed() {
        //Prevents back button from being pressed
    }
}
