package com.android.hostel.network.builder;
/*
 * Created by DangQuang on 12/17/17
 */

import java.util.HashMap;
import java.util.Map;

public class ApiParams {

    private Map<String, Object> mParams = new HashMap<>();
    public void add(String key, Object value) {
        mParams.put(key, value);
    }
    public Map<String, Object> getParams() {
        return mParams;
    }
}
