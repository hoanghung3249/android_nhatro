package com.android.hostel.network.builder;
/*
 * Created by DangQuang on 12/17/17
 */

import com.android.hostel.base.BaseApplication;
import com.android.hostel.interfaces.ApiRequestListener;

public class ApiClient extends ApiClientBuilder {

    public ApiClient(ApiRequestListener listener, BaseApplication baseApp) {
        super(listener, baseApp);
    }

    //--------------API Auth----------------------------------------------------------
}
