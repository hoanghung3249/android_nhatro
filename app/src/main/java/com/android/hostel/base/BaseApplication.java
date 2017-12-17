package com.android.hostel.base;
/*
 * Created by DangQuang on 12/17/17
 */

import android.app.Application;

import com.android.hostel.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

//    private UserModel userModel;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_avenirNext_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

//    public UserModel getUserModel() {
//        return SharedPrefUtils.getUser(this);
//    }
//
//    public void setUserModel(UserModel userModel) {
//        this.userModel = userModel;
//    }
}
