package com.mawwad.jobfinder.Data;

import android.content.Context;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.MapView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mawwad.jobfinder.activities.ResultActivity;
import com.mawwad.jobfinder.model.Filter;
import com.mawwad.jobfinder.model.Job;
import com.mawwad.jobfinder.model.Provider;
import com.mawwad.jobfinder.model.SearchUrlParameterKeys;
import com.mawwad.jobfinder.utility.JobMapper;
import com.mawwad.jobfinder.volley.CustomVolley;
import com.mawwad.jobfinder.volley.VolleyErrorCustom;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private static  DataLoader instance ;
    public List<Provider> providerList ;
    private Context context;
    public List<Job> jobList;
    public List<String> availablePosition ;

    public static synchronized DataLoader getInstance() {
        if(instance == null){
            instance = new DataLoader();
        }
        return instance;
    }

    private DataLoader(){
        jobList = new ArrayList<>();
        providerList = new ArrayList<>();
        availablePosition = new ArrayList<>();
    }

    public void init(Context context){
        this.context = context ;
        loadProviders();
        getAllJobs();
    }

    private void loadProviders() {
        String providersJson = null;
        try {
            InputStream inputStream = context.getAssets().open("providers.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            providersJson = new String(buffer, "UTF-8");

            Gson gson = new GsonBuilder().create();
            Type providerListType = new TypeToken<List<Provider>>() {}.getType();
            providerList = gson.fromJson(providersJson, providerListType);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void getAllJobs() {
        for (Provider provider : providerList) {
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, provider.getSearchBaseUrl(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Job job = JobMapper.getJobFromResponse(jsonObject, provider.getSearchJobResponseParamterKeys());
                            jobList.add(job);
                            availablePosition.add(job.getTitle());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, new VolleyErrorCustom(context));
            CustomVolley.getInstance().addJsonArrayRequest(jsonObjectRequest);
        }
    }

    public void loadData(Filter filter) throws JSONException {
        jobList.clear();
        if(filter.getProvider() == null){
            for(Provider  provider : providerList){

                String url = getUrl(provider.getSearchBaseUrl(), provider.getSearchUrlParameterKeys(), filter);
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Job job = JobMapper.getJobFromResponse(jsonObject, provider.getSearchJobResponseParamterKeys());
                                jobList.add(job);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        EventBus.getDefault().post(new ResultActivity.FindJobResponse());

                    }
                }, new VolleyErrorCustom(context));
                CustomVolley.getInstance().addJsonArrayRequest(jsonObjectRequest);
            }
        }else{

            String url = getUrl(filter.getProvider().getSearchBaseUrl(), filter.getProvider().getSearchUrlParameterKeys(), filter);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Job job = JobMapper.getJobFromResponse(jsonObject, filter.getProvider().getSearchJobResponseParamterKeys());
                            jobList.add(job);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    EventBus.getDefault().post(new ResultActivity.FindJobResponse());


                }
            }, new VolleyErrorCustom(context));
            CustomVolley.getInstance().addJsonArrayRequest(jsonObjectRequest);
        }
    }

    private String getUrl(String searchBaseUrl, SearchUrlParameterKeys searchUrlParameterKeys, Filter filter) {
        String url = searchBaseUrl+"?" ;
        if(searchUrlParameterKeys.getPosition()!=null &&(filter.getPosition()!= null && !filter.getPosition().isEmpty()))
            url+=searchUrlParameterKeys.getPosition()+"="+filter.getPosition()+"&";
        if(searchUrlParameterKeys.getLat()!= null&&(filter.getLat()!= null && !filter.getLat().isEmpty()))
            url+=searchUrlParameterKeys.getLat()+"="+filter.getLat()+"&";
        if(searchUrlParameterKeys.getLng() != null&&(filter.getLng()!= null && !filter.getLng().isEmpty()))
            url+=searchUrlParameterKeys.getLng()+"="+filter.getLng()+"&";
        if(searchUrlParameterKeys.getLatLng() != null&&(filter.getLatLng()!= null && !filter.getLatLng().isEmpty()))
            url+=searchUrlParameterKeys.getLatLng()+"="+filter.getLatLng();
        return url;
    }

    private Map<String, String> getRequestParams(SearchUrlParameterKeys searchUrlParameterKeys, Filter filter) {
        Map<String,String> parameters = new HashMap<>();
        if(searchUrlParameterKeys.getPosition()!=null)
        parameters.put(searchUrlParameterKeys.getPosition(),filter.getPosition());
        if(searchUrlParameterKeys.getLat()!= null)
        parameters.put(searchUrlParameterKeys.getLat(),filter.getLat());
        if(searchUrlParameterKeys.getLng() != null)
        parameters.put(searchUrlParameterKeys.getLng(),filter.getLng());
        if(searchUrlParameterKeys.getLatLng() != null)
        parameters.put(searchUrlParameterKeys.getLatLng(),filter.getLatLng());
        return parameters;
    }
}
