package com.mawwad.jobfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchUrlParameterKeys implements Serializable {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("latLng")
    @Expose
    private String latLng;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("pageSize")
    @Expose
    private String pageSize;
    @SerializedName("pagination")
    @Expose
    private String pagination;
    @SerializedName("fromRecord")
    @Expose
    private String fromRecord;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getFromRecord() {
        return fromRecord;
    }

    public void setFromRecord(String fromRecord) {
        this.fromRecord = fromRecord;
    }

}