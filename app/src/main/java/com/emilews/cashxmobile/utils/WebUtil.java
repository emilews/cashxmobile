package com.emilews.cashxmobile.utils;

import android.content.Context;
import android.os.Handler;

import com.emilews.cashxmobile.common.Constants;
import com.emilews.cashxmobile.utils.http.BchRestAPI;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebUtil {
    /*
        Utility to get data from web servers, to be precise, from Bitcoin Cash's REST API. We use it
        to get balances, make raw transactions, etc.
     */
    public static String[] getBCHAddressData(Context context, String address){
        Handler handler = new Handler();
        final String[] data = new String[1];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.getSingleAdressUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                BchRestAPI service = retrofit.create(BchRestAPI.class);
                Call<String> result = service.getAddress(address);
                try {
                    Response<String> s = result.execute();
                    data[0] = s.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(data[0]);
            }
        };
        handler.post(runnable);
        return data;
    }
}

