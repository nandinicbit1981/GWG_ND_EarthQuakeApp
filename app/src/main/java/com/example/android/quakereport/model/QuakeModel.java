package com.example.android.quakereport.model;

/**
 * Created by nandpa on 4/26/18.
 */

public class QuakeModel {
    private float magnitude;
    private String location;
    private String time;
    public QuakeModel(float magnitude, String location, String time) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public float getMagnitude() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
