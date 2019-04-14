package com.mawwad.jobfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchJobResponseParamterKeys implements Serializable {

    @SerializedName("companyLogo")
    @Expose
    private String companyLogo;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("postDate")
    @Expose
    private String postDate;
    @SerializedName("postDateFormat")
    @Expose
    private String postDateFormat;
    @SerializedName("url")
    @Expose
    private String url;

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostDateFormat() {
        return postDateFormat;
    }

    public void setPostDateFormat(String postDateFormat) {
        this.postDateFormat = postDateFormat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}