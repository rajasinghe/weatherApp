package com.example.weatherapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private String name;

    public Location(String name, String location, ArrayList<Day> days) {
        this.name = name;
        this.location = location;
        this.days = days;
    }

    private String location;
    private ArrayList<Day> days;

    public ArrayList<Day> getDays() {
        return days;
    }
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
