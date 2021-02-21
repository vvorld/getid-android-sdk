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
import com.sdk.getidlib.model.app.document.CountryDocumentConfig;
import com.sdk.getidlib.model.app.form.CategoryType;
import com.sdk.getidlib.model.app.form.FormConfig;
import com.sdk.getidlib.model.app.form.FormField;
import com.sdk.getidlib.model.app.form.FormValueType;
import com.sdk.getidlib.model.data.service.JwtService;
import com.sdk.getidlib.model.entity.jwt.JwtResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.HttpException;
import retrofit2.Response;

public class JavaAppPresenter {
    private JavaAppActivity view = null;
    private static final String GET_ID_SDK_DOMAIN = "API_URL";

    public JavaAppPresenter(JavaAppActivity view) {
        this.view = view;
    }

    public void setupSdk() {

        List<FlowScreens> flowItems = new ArrayList<>();
        flowItems.add(FlowScreens.SCREEN_FORM);
        flowItems.add(FlowScreens.SCREEN_DOCUMENT);
        flowItems.add(FlowScreens.SCREEN_SELFIE);
        flowItems.add(FlowScreens.SCREEN_LIVENESS);
        flowItems.add(FlowScreens.SCREEN_THANKS);

        ArrayList<FormField> fields = new ArrayList<>();
        Map<String, ArrayList<FormField>> formMap = new HashMap<>();
        fields.add(new FormField("First name", "First name", FormValueType.TEXT, CategoryType.FIRST_NAME,
                new ArrayList<>(), null, "", false, false));
        fields.add(new FormField("Last name", "Last name", FormValueType.TEXT, CategoryType.LAST_NAME,
                new ArrayList<>(), null, "", false, false));
        fields.add(new FormField("Date Of Birth", "Date Of Birth", FormValueType.DATE, CategoryType.DATE_OF_BIRTH,
                new ArrayList<>(), null, "", false, false));

        formMap.put("Form", fields);
        FormConfig formConfig = new FormConfig(false, formMap);

        CountryDocumentConfig documentConfig = new CountryDocumentConfig(null, null, false, true, Collections.emptyMap(), true);

        ArrayList<VerificationTypesEnum> verificationTypes = new ArrayList<>();
        verificationTypes.add(VerificationTypesEnum.DATA_EXTRACTION);
        verificationTypes.add(VerificationTypesEnum.WATCHLISTS);

        Map<String,String> metadata = new HashMap<>();
        metadata.put("dapartment","EST");

        ConfigurationPreset configPreset = new ConfigurationPreset(
                flowItems,
                formConfig,
                null,
                null,
                null,
                documentConfig,
                verificationTypes,
                metadata,
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
