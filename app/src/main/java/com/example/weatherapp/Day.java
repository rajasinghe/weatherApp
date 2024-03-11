package com.example.weatherapp;

import java.util.ArrayList;

public class Day {
    private ArrayList<Hour> hours;
    private String date;
    private double tempMax;
    private double tempMin;
    private double temp;
    private String sunRise;
    private String sunSet;

    private String description;

    public Day(ArrayList<Hour> hours, String date, double tempMax, double tempMin, double temp, String sunRise, String sunSet, String description) {
        this.hours = hours;
        this.date = date;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.temp = temp;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Hour> getHours() {
        return hours;
    }
    public void setHours(ArrayList<Hour> hours) {
        this.hours = hours;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getTempMax() {
        return tempMax;
    }
    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
    public double getTempMin() {
        return tempMin;
    }
    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }
    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public String getSunRise() {
        return sunRise;
    }
    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }
    public String getSunSet() {
        return sunSet;
    }
    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }
}
