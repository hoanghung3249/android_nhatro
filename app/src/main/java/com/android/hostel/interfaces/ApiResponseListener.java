package com.android.hostel.interfaces;
/*
 * Created by HoangDong on 08/11/2017.
 */


import com.android.hostel.base.BaseModel;
import com.android.hostel.model.ErrorModel;

public interface ApiResponseListener {
    void onDataError(int nCode, ErrorModel t);

    void onDataResponse(int nCode, BaseModel nData);
}
