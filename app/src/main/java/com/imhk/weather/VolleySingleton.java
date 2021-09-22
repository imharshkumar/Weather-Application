package com.imhk.weather;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    static VolleySingleton mInstance;
    RequestQueue mrequestqueue;

    public VolleySingleton(Context context) {
        mrequestqueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if(mInstance==null) {
            mInstance=new VolleySingleton(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue () {
        return mrequestqueue;
    }
}
