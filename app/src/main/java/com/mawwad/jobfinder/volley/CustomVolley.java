package com.mawwad.jobfinder.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CustomVolley {

    private static CustomVolley instance ;
    private RequestQueue requestQueue ;

    public static synchronized CustomVolley getInstance(){
        if(instance == null){
            instance = new CustomVolley();
        }
        return instance;
    }

    public void init(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    private CustomVolley(){
    }

    public void addJsonRequest(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }

    public void addStringRequest(StringRequest stringRequest){
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public void addJsonArrayRequest(JsonArrayRequest jsonObjectRequest) {
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
