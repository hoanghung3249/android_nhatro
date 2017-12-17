package com.android.hostel.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.android.hostel.interfaces.DialogListener;

/**
 * Created by DangQuang on 12/17/17.
 **/

public class AppUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showAlert(final Context context, String title, String content, @Nullable final DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(content);
//        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
//            if (listener != null) {
//                listener.onConfirmClicked();
//            }
//        });
        builder.create().show();
    }

}
