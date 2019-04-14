package com.mawwad.jobfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Provider implements Serializable {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("searchBaseUrl")
    @Expose
    private String searchBaseUrl;
    @SerializedName("httpMethod")
    @Expose
    private String httpMethod;
    @SerializedName("searchUrlParameterKeys")
    @Expose
    private SearchUrlParameterKeys searchUrlParameterKeys;
    @SerializedName("searchJobResponseParamterKeys")
    @Expose
    private SearchJobResponseParamterKeys searchJobResponseParamterKeys;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSearchBaseUrl() {
        return searchBaseUrl;
    }

    public void setSearchBaseUrl(String searchBaseUrl) {
        this.searchBaseUrl = searchBaseUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public SearchUrlParameterKeys getSearchUrlParameterKeys() {
        return searchUrlParameterKeys;
    }

    public void setSearchUrlParameterKeys(SearchUrlParameterKeys searchUrlParameterKeys) {
        this.searchUrlParameterKeys = searchUrlParameterKeys;
    }

    public SearchJobResponseParamterKeys getSearchJobResponseParamterKeys() {
        return searchJobResponseParamterKeys;
    }

    public void setSearchJobResponseParamterKeys(SearchJobResponseParamterKeys searchJobResponseParamterKeys) {
        this.searchJobResponseParamterKeys = searchJobResponseParamterKeys;
    }

    @NonNull
    @Override
    public String toString() {
        return displayName;
    }
}