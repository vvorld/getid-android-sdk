package com.sdk.getid.presentation.java;

import android.content.Context;
import android.util.Log;

import com.sdk.getid.app.AndroidApplication;
import com.sdk.getid.model.app.network.RetrofitFactory;
import com.sdk.getid.ui.java.JavaAppActivity;
import com.sdk.getidlib.config.ConfigurationPreset;
import com.sdk.getidlib.config.FlowScreens;
import com.sdk.getidlib.config.GetIDFactory;
import com.sdk.getidlib.config.VerificationTypesEnum;
import com.sdk.getidlib.model.data.service.JwtService;
import com.sdk.getidlib.model.entity.jwt.JwtResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.HttpException;
import retrofit2.Response;

public class JavaAppPresenter {
    private JavaAppActivity view = null;
    private static final String GET_ID_SDK_DOMAIN = "https://gerus.dev.getid.dev/";

    public JavaAppPresenter(JavaAppActivity view) {
        this.view = view;
    }

    public void setupSdk() {

        List<FlowScreens> flowItems = new ArrayList<>();
        flowItems.add(FlowScreens.SCREEN_CONSENT);
        flowItems.add(FlowScreens.SCREEN_FORM);
        flowItems.add(FlowScreens.SCREEN_DOCUMENT);
        flowItems.add(FlowScreens.SCREEN_SELFIE);
        flowItems.add(FlowScreens.SCREEN_LIVENESS);

        ArrayList<VerificationTypesEnum> verificationTypes = new ArrayList<>();
        verificationTypes.add(VerificationTypesEnum.FACE_MATCHING);
        verificationTypes.add(VerificationTypesEnum.DATA_EXTRACTION);

        ConfigurationPreset configPreset = new ConfigurationPreset(
                flowItems,
                null,
                null,
                null,
                null,
                null,
                verificationTypes,
                null,
                null);

        List<Locale> locale = new ArrayList<>();
        locale.add(Locale.ENGLISH);

        RetrofitFactory retrofitFactory = new RetrofitFactory();
        JwtService retrofit = retrofitFactory.initRetrofit().create(JwtService.class);
        Context application = AndroidApplication.Companion.getSInstance();

        new Thread(() -> {
            try {
                Response<JwtResponse> request = retrofit.getJwt().execute();
                JwtResponse response = handleRequest(request);
                if (response.getToken() != null && !response.getToken().isEmpty()) {
                    GetIDFactory getIDFactory = new GetIDFactory();
                    getIDFactory.setup(application, configPreset, response.getToken(), GET_ID_SDK_DOMAIN, locale, null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private JwtResponse handleRequest(Response<JwtResponse> response) {
        JwtResponse handledResponse = null;
        try {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    handledResponse = response.body();
                }
            }
        } catch (HttpException e) {
            Log.e("token", e.message(), e);
        } catch (Throwable e) {
            Log.e("token", e.getMessage(), e);
        }

        return handledResponse;
    }
}
