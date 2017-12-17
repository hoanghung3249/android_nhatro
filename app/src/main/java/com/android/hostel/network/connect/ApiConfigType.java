package com.android.hostel.network.connect;
/*
 * Created by DangQuang on 12/17/17
 */

public enum ApiConfigType {
    STAGING("staging"),
    DEV("dev"),
    LIVE("live");

    private String mTitle;

    ApiConfigType(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
