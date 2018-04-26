package com.example.android.quakereport.model;

import android.net.Uri;

/**
 * Created by nandpa on 4/26/18.
 */

public class QuakeModel {
    private double magnitude;
    private String location;
    private long date;
    private Uri url;
    public QuakeModel(double magnitude, String location, long date, Uri url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }
}
