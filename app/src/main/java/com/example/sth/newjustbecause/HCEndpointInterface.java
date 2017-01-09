package com.example.sth.newjustbecause;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by rados on 09.01.2017.
 */

public interface HCEndpointInterface {
    @Headers({"authorization: Basic YWRtaW46YWRtaW4="})
    @POST("api/devices/33/action/turnOff")
    Call<RequestBody> turnOffDimmer();

    @Headers({"authorization: Basic YWRtaW46YWRtaW4="})
    @POST("api/devices/33/action/turnOn")
    Call<RequestBody> turnOnDimmer();
}
