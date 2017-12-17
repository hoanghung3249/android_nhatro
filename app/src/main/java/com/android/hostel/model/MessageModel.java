package com.android.hostel.model;
/*
 * Created by DangQuang on 12/17/17
 */

import com.android.hostel.base.BaseModel;
import com.google.gson.annotations.SerializedName;

public class MessageModel extends BaseModel {
    @SerializedName("message")
    public String message;
}
