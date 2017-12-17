package com.android.hostel.network.handler;
/*
 * Created by DangQuang on 12/17/17
 */

import android.support.annotation.NonNull;

import com.android.hostel.R;
import com.android.hostel.base.BaseActivity;
import com.android.hostel.base.BaseModel;
import com.android.hostel.interfaces.ApiRequestListener;
import com.android.hostel.interfaces.ApiResponseListener;
import com.android.hostel.model.ErrorModel;
import com.android.hostel.utils.AppUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiBaseHandler {
    private BaseActivity mActivity;
    private ApiResponseListener mResponseListener;
    public ApiRequestListener requestListener = (nCode, nType, call) -> {
        boolean isNetwork = AppUtils.isNetworkAvailable(mActivity);
        if (call != null && isNetwork) {
            if (call.isExecuted()) {
                call.cancel();
            }
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                    BaseModel mData = null;
                    if (response.body() != null) {
                        Gson gson = new Gson();
                        mData = gson.fromJson(response.body(), nType);
                    }
                    onDataResponse(nCode, mData);
                }

                @Override
                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                    mResponseListener.onDataError(nCode, ErrorModel.parseData(t.getMessage()));
                    mActivity.dismissLoading();
                }
            });
        } else {
            if (!isNetwork)
                AppUtils.showAlert(mActivity, mActivity.getString(R.string.error_network),
                        mActivity.getString(R.string.error_no_internet), null);
        }
    };

    public ApiBaseHandler(BaseActivity activity, ApiResponseListener responseListener) {
        this.mActivity = activity;
        this.mResponseListener = responseListener;

    }

    private void onDataResponse(int nCode, BaseModel nData) {
        mResponseListener.onDataResponse(nCode, nData);
    }
}
