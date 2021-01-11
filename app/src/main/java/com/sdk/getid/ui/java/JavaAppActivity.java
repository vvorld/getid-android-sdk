package com.sdk.getid.ui.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdk.getid.R;
import com.sdk.getid.presentation.java.JavaAppPresenter;
import com.sdk.getidlib.config.ConfigurationPreset;
import com.sdk.getidlib.config.FlowScreens;
import com.sdk.getidlib.config.VerificationTypesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JavaAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_java);
        JavaAppPresenter presenter = new JavaAppPresenter(this);

        findViewById(R.id.btn_begin).setOnClickListener(v -> {
            presenter.setupSdk();
        });
    }
}