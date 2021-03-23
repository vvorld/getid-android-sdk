package com.sdk.getid.ui.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdk.getid.R;
import com.sdk.getidlib.config.GetIDSDK;
import com.sdk.getidlib.model.app.auth.Key;

public class JavaAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_java);

        findViewById(R.id.btn_begin).setOnClickListener(v -> {
            new GetIDSDK().startVerificationFlow(
                    getApplicationContext(),
                    "API_URL",
                    new Key("SDK_KEY", null),
                    "FLOW_NAME",
                    null,
                    null,
                    null,
                    null
            );
        });
    }
}