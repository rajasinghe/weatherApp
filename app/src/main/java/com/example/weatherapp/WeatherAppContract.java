package com.example.weatherapp;

import android.provider.BaseColumns;

public final class WeatherAppContract {
    private WeatherAppContract(){

    }
    public static class Locations implements BaseColumns{
        public static final String TABLE_NAME="locations";
        public  static final String LOCATION_NAME="location";

        public static final String COORDINATES="coordinates";

        public static final String NICKNAME="nickName";

        public static final String IS_PRIMARY="isPrimary";

        public static final String SQL_CREATE_ENTRIES=
                "CREATE TABLE "+Locations.TABLE_NAME+"("+Locations.COORDINATES +"TEXT PRIMARY KEY,"+
                 Locations.LOCATION_NAME+" TEXT,"+Locations.NICKNAME+" TEXT,"+Locations.IS_PRIMARY+" INTEGER)";

        public static final String SQL_DELETE_ENTRIES=
                "DROP TABLE IF EXISTS"+Locations.TABLE_NAME;
    }

}
