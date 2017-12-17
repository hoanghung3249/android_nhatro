package com.android.hostel.network.connect;
/*
 *Created by DangQuang on 12/17/17
 */

public class ApiConfigDetail {
    private String mBaseURL;
    private String mApiKey;

    public String getBaseURL() {
        return mBaseURL;
    }

    public void setBaseURL(String mBaseURL) {
        this.mBaseURL = mBaseURL;
    }
    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        this.mApiKey = apiKey;
    }
}
