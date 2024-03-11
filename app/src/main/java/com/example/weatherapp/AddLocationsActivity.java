package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class AddLocationsActivity extends AppCompatActivity {
WeatherAppDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);
        dbHelper=new WeatherAppDbHelper(this);

    }

    private boolean addLocation(String coordinates,String nickName,String cityName,int isPrimary){
        ContentValues values=new ContentValues();
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        if(isPrimary==1){
            ContentValues values1=new ContentValues();
            values1.put(WeatherAppContract.Locations.IS_PRIMARY,0);
            String selection=WeatherAppContract.Locations.IS_PRIMARY + "LIKE ?";
            String []selectionArgs={"1"};
            int updatedCount=database.update(WeatherAppContract.Locations.TABLE_NAME,values1,selection,selectionArgs);
        }
        values.put(WeatherAppContract.Locations.COORDINATES,coordinates);
        values.put(WeatherAppContract.Locations.LOCATION_NAME,cityName);
        values.put(WeatherAppContract.Locations.IS_PRIMARY,isPrimary);
        values.put(WeatherAppContract.Locations.NICKNAME,nickName);
        return database.insert(WeatherAppContract.Locations.TABLE_NAME,null,values) != -1;
    }
}