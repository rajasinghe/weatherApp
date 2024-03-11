package com.example.weatherapp;

public class Hour {
    private String cloudIcon;
    private String hour;
    private double temp;
    private double windspeed;
    private double windDirection;
    private double cloudCover;
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getCloudIcon() {
        return cloudIcon;
    }

    public void setCloudIcon(String cloudIcon) {
        this.cloudIcon = cloudIcon;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Hour(
                String hour,
                double temp,
                double windspeed,
                double windDirection,
                double cloudCover,
                String cloudIcon) {
        this.cloudIcon = cloudIcon;
        this.hour = hour;
        this.temp = temp;
        this.windspeed = windspeed;
        this.windDirection = windDirection;
        this.cloudCover = cloudCover;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
