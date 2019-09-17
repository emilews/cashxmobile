package com.emilews.cashxmobile.utils;

import android.content.Context;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.emilews.cashxmobile.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class WebUtil {
    /*
        Utility to get data from web servers, to be precise, from Bitcoin Cash's REST API. We use it
        to get balances, make raw transactions, etc.
     */
    public static JSONObject getBCHAddressData(Context context, String address){
        Handler handler = new Handler();
        final JSONObject[] data = new JSONObject[1];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = Constants.getSingleAdressUrl();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url + address, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                data[0] = response;
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                            }
                        });
                // Add the request to the RequestQueue.
                queue.add(jsonObjectRequest);
                queue.start();
            }
        };
        handler.post(runnable);
        return data[0];
    }
}
