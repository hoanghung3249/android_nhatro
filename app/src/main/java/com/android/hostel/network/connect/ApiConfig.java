package com.android.hostel.network.connect;
/*
 * Created by DangQuang on 12/17/17
 */

public class ApiConfig {
    public static ApiConfigDetail createConnectionDetail(ApiConfigType type) {
        ApiConfigDetail connection = new ApiConfigDetail();
        if (type == null) {
            type = ApiConfigType.DEV;
        }
        switch (type) {
            case DEV:
                connection.setBaseURL("http://fxchange.acc-svrs.com/api/v1/");
                connection.setApiKey("DEVACCRMLEXCHANGE03052017");
                break;
            case STAGING:
                connection.setBaseURL("http://stg-novu.rmlbs.co/api/v1/");
                connection.setApiKey("STGACCRMLEXCHANGE26122017");
                break;
            case LIVE:
                connection.setBaseURL("");
                connection.setApiKey("LIVEACCRMLEXCHANGE03032017");
                break;
            default:
                break;
        }
        return connection;
    }
}
