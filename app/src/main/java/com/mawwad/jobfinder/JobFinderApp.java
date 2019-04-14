package com.mawwad.jobfinder;

import android.app.Application;

import com.mawwad.jobfinder.Data.DataLoader;
import com.mawwad.jobfinder.volley.CustomVolley;


public class JobFinderApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomVolley.getInstance().init(this);
        DataLoader.getInstance().init(this);
    }

}
