package com.mawwad.jobfinder.activities;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mawwad.jobfinder.Data.DataLoader;
import com.mawwad.jobfinder.R;
import com.mawwad.jobfinder.model.Filter;
import com.mawwad.jobfinder.model.Job;
import com.mawwad.jobfinder.model.Provider;
import com.mawwad.jobfinder.utility.JobMapper;
import com.mawwad.jobfinder.volley.CustomVolley;
import com.mawwad.jobfinder.volley.VolleyErrorCustom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {


    public static final String FILTER_KEY = "filter";


    @BindView(R.id.job_recycler_view)
    RecyclerView jobRecycler;
    @BindView(R.id.loadingPanel)
    LinearLayout loadingView;
    private JobAdapter jobAdapter;
    private Filter filter ;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        filter = (Filter) getIntent().getSerializableExtra(FILTER_KEY);
        loadingView.setVisibility(View.VISIBLE);
        try {
            DataLoader.getInstance().loadData(filter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void initJobsRecycler() {
        jobAdapter = new JobAdapter(DataLoader.getInstance().jobList);
        jobRecycler.setAdapter(jobAdapter);
        jobRecycler.setHasFixedSize(true);
        jobRecycler.setItemViewCacheSize(20);
        jobRecycler.setDrawingCacheEnabled(true);
        jobRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        // Set padding for Tiles
        LinearLayoutManager layout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        jobRecycler.setLayoutManager(layout);
        loadingView.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJobResponse(FindJobResponse findJobResponse){
        initJobsRecycler();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                onBackPressed();

                return true;

            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }

    public static class FindJobResponse {
    }
}
