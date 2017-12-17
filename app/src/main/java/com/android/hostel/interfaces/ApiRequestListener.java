package com.android.hostel.interfaces;
/*
 * Created by HoangDong on 08/11/2017.
 */

import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import retrofit2.Call;

public interface ApiRequestListener {
    void onRequestApi(int nCode, Type mType, Call<JsonObject> call);

}
