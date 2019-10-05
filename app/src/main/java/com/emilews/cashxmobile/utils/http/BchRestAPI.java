package com.emilews.cashxmobile.utils.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BchRestAPI {
    @GET("v2/address/details/{address}")
    Call<String> getAddress(@Path("address") String address);
}
