package com.android.hostel.model;
/*
 * Created by DangQuang on 12/17/17
 */

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import okhttp3.Response;

public class ErrorModel {

    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code = -1;

    public String getMessage() {
        if (TextUtils.isEmpty(message)) return "Opps! Some things wrong.";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ErrorModel parseData(String json) {
        ErrorModel errorModel;
        try {
            Gson gson = new Gson();
            errorModel = gson.fromJson(json, ErrorModel.class);
            if (errorModel != null) return errorModel;
            errorModel = new ErrorModel();
            errorModel.setMessage(json);
        } catch (Exception e) {
            errorModel = new ErrorModel();
            errorModel.setMessage(json);
        }
        return errorModel;
    }

    public static String getErrorString(Response response) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(response.code());
        String body = response.body().toString();
        if (!body.startsWith("<!DOCTYPE HTML")) {
            errorModel.setMessage(body);
        } else {
            errorModel.setMessage("");
        }
        return errorModel.toString();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
