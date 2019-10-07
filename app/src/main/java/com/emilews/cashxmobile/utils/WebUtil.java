package com.emilews.cashxmobile.utils;

import android.content.Context;
import android.os.Handler;

import com.emilews.cashxmobile.controllers.AddressDataController;

public class WebUtil {
    /*
        Utility to get data from web servers, to be precise, from Bitcoin Cash's REST API. We use it
        to get balances, make raw transactions, etc.
     */
    public static void getBCHAddressData(String address){
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AddressDataController controller = new AddressDataController();
                controller.start(address);
            }
        };
        handler.post(runnable);
    }
}

