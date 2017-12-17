package com.android.hostel.base;
/*
 * Created by DangQuang on 12/17/17
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.hostel.R;
import com.android.hostel.interfaces.ApiResponseListener;
import com.android.hostel.model.ErrorModel;
import com.android.hostel.network.builder.ApiClient;
import com.android.hostel.network.handler.ApiBaseHandler;
import com.android.hostel.others.view.LoadingDialog;
import com.android.hostel.utils.AppUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity implements ApiResponseListener {

    public ApiClient mApiClient;
    View.OnClickListener onBackClick = view -> onBackPressed();
    private LoadingDialog mDialogView;

//    public void setActionBarBack(View view, String title) {
//        TextView tvTitle = view.findViewById(R.id.actionbarBack_tvTitle);
//        ImageView imvBack = view.findViewById(R.id.actionbarBack_imvBack);
//        if (tvTitle != null) {
//            tvTitle.setText(title);
//        }
//        if (imvBack != null) {
//            imvBack.setOnClickListener(onBackClick);
//        }
//
//    }
//
//    public void setActionBarClose(View view, String title) {
//        TextView tvTitle = view.findViewById(R.id.actionbarClose_tvTitle);
//        ImageView imvClose = view.findViewById(R.id.actionbarClose_imvClose);
//        if (tvTitle != null) {
//            tvTitle.setText(title);
//        }
//        if (imvClose != null) {
//            imvClose.setOnClickListener(onBackClick);
//        }
//    }
//
//    public void setActionBarTitle(View view , String title){
//        TextView tvTitle = view.findViewById(R.id.actionbarTitle_tvTitle);
//        if (tvTitle != null) {
//            tvTitle.setText(title);
//        }
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiBaseHandler mBaseHandler = new ApiBaseHandler(this, this);
        mApiClient = new ApiClient(mBaseHandler.requestListener, (BaseApplication) getApplication());
    }

    public void showLoading() {
        if (AppUtils.isNetworkAvailable(this))
            if (mDialogView != null) {
                mDialogView.show();
            } else {
                mDialogView = new LoadingDialog(this);
                mDialogView.setCanceledOnTouchOutside(false);
                mDialogView.show();
            }
    }

    public void dismissLoading() {
        if (mDialogView != null) {
            mDialogView.dismiss();
        }
    }

    private void addReplaceFragment(BaseFragment fragment, boolean isReplace, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null && fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (isReplace)
                fragmentTransaction.replace(R.id.frmContainer, fragment);
            else {
                android.support.v4.app.Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frmContainer);
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment);
                }
                fragmentTransaction.add(R.id.frmContainer, fragment, fragment.getClass().getSimpleName());
            }
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(BaseFragment fragment, boolean isAddToBackStack) {

        addReplaceFragment(fragment, true, isAddToBackStack);
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackStack) {
        addReplaceFragment(fragment, false, isAddToBackStack);
    }

    public void clearAllBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public void onDataError(int nCode, ErrorModel error) {
        if (error.getMessage().contains(getString(R.string.error_parse_json)) || error.getMessage().contains(getString(R.string.error_parse_type)))
            AppUtils.showAlert(this, getString(R.string.error), getString(R.string.error_server), null);
        else
            AppUtils.showAlert(this, getString(R.string.error), error.getMessage(), null);

    }

    @Override
    public void onDataResponse(int nCode, BaseModel nData) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
