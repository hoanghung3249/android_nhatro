package com.android.hostel.network.builder;
/*
 * Created by DangQuang on 12/17/17
 */

import com.android.hostel.base.BaseApplication;
import com.android.hostel.interfaces.ApiRequestListener;
import com.android.hostel.others.constant.ApiKey;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import retrofit2.Call;

abstract class ApiClientBuilder {
    private ApiRequestListener mListener;
    private String mApiKey;
    private BaseApplication baseApp;

    ApiClientBuilder(ApiRequestListener listener, BaseApplication baseApp) {
        this.mListener = listener;
        this.baseApp = baseApp;
    }

    void requestApi(Type nType, Call<JsonObject> call) {
        if (mListener != null) {
            mListener.onRequestApi(ApiKey.CODE_DEFAULT, nType, call);
        }
    }

    void requestApi(int nCode, Type nType, Call<JsonObject> call) {
        if (mListener != null) {
            mListener.onRequestApi(nCode, nType, call);
        }
    }

//    void addApiKey(ApiParams params) {
//        if (TextUtils.isEmpty(mApiKey))
//            mApiKey = ApiConfig.createConnectionDetail(AppConfig.mConnectType).getApiKey();
//        params.add(ApiKey.API_KEY, mApiKey);
//    }

//    String getToken() {
//        return baseApp != null ? baseApp.getUserModel().getAccessToken() : "";
//    }
}
