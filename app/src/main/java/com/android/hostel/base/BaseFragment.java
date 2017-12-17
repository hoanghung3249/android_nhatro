package com.android.hostel.base;
/*
 * Created by DangQuang on 12/17/17
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.android.hostel.R;
import com.android.hostel.interfaces.ApiResponseListener;
import com.android.hostel.interfaces.FragmentResultListener;
import com.android.hostel.model.ErrorModel;
import com.android.hostel.network.builder.ApiClient;
import com.android.hostel.network.handler.ApiBaseHandler;
import com.android.hostel.utils.AppUtils;

public abstract class BaseFragment extends Fragment implements ApiResponseListener {
    public ApiClient mApiClient;
    private FragmentResultListener resultListener;
    private int mCodeRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiBaseHandler mBaseHandler = new ApiBaseHandler((BaseActivity) getActivity(), this);
        mApiClient = new ApiClient(mBaseHandler.requestListener, (BaseApplication) getActivity().getApplication());

    }


    public void setFragmentForResult(int codeRequest, FragmentResultListener fragmentResultListener) {
        this.resultListener = fragmentResultListener;
        this.mCodeRequest = codeRequest;
    }

    protected void callBackFragmentResult(Bundle bundle) {
        if (resultListener != null)
            resultListener.onFragmentForResult(mCodeRequest, bundle);
    }

//    public void setActionBarBack(View view, String title) {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).setActionBarBack(view, title);
//        }
//    }
//
//    public void setActionBarClose(View view, String title) {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).setActionBarClose(view, title);
//        }
//    }
//
//    public void setActionBarTitle(View view, String title) {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).setActionBarTitle(view, title);
//        }
//    }

    public void clearAllBackStack() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).clearAllBackStack();
        }
    }

    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).replaceFragment(fragment, isAddToBackStack);
        }
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackStack) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).addFragment(fragment, isAddToBackStack);
        }
    }

    public void showLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    public void dismissLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).dismissLoading();
        }
    }

    public void clearErrorText(TextView[] textViews) {
        for (TextView textView : textViews)
            textView.setText("");
    }


    @Override
    public void onDataError(int nCode, ErrorModel error) {
        if (error.getMessage().contains(getString(R.string.error_parse_json)) || error.getMessage().contains(getString(R.string.error_parse_type)))
            AppUtils.showAlert(getActivity(), getString(R.string.error), getString(R.string.error_server), null);
        else
            AppUtils.showAlert(getActivity(), getString(R.string.error), error.getMessage(), null);

    }

    @Override
    public void onDataResponse(int nCode, BaseModel nData) {

    }

}
