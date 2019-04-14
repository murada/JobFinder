package com.mawwad.jobfinder.utility;

import com.mawwad.jobfinder.model.Job;
import com.mawwad.jobfinder.model.SearchJobResponseParamterKeys;

import org.json.JSONException;
import org.json.JSONObject;

public class JobMapper {


    public static synchronized Job getJobFromResponse(JSONObject jsonObject ,SearchJobResponseParamterKeys searchJobResponseParamterKeys){
        Job job = new Job();
        try {
            job.setCompany(jsonObject.getString(searchJobResponseParamterKeys.getCompanyName()));
            job.setTitle(jsonObject.getString(searchJobResponseParamterKeys.getJobTitle()));
            if(jsonObject.has(searchJobResponseParamterKeys.getCompanyLogo()))
            job.setCompanyLogo(jsonObject.getString(searchJobResponseParamterKeys.getCompanyLogo()));
            job.setJobUrl(jsonObject.getString(searchJobResponseParamterKeys.getUrl()));
            job.setLocation(jsonObject.getString(searchJobResponseParamterKeys.getLocation()));
            job.setPostedDate(jsonObject.getString(searchJobResponseParamterKeys.getPostDate()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return job;
    }

}
