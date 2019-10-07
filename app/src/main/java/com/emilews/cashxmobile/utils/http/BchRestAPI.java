package com.emilews.cashxmobile.utils.http;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BchRestAPI {
    @GET("v2/address/details/{address}")
    Call<JsonObject> getAddress(@Path("address") String address);
}
