package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalTime;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    String TAG = "main activity";
    LocalDate currentDate;
    ArrayList<Location> locations;
    ListView listView;
    RecyclerView hourlyForecastRecycler;
    Handler handler;

    View views;

    Button addLocationBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(Looper.getMainLooper());
        currentDate = LocalDate.now();
        hourlyForecastRecycler = findViewById(R.id.hourly_forecaset);
        hourlyForecastRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollToPositionOfHourlyRecycler(LocalTime.now().getHour());
                        }
                    }, 5000);
                }
            }
        });

        locations = new ArrayList<Location>();
        getData("6.4738,79.9920");

        addLocationBtn = findViewById(R.id.addCity);
        addLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_addLocation_activity = new Intent(getBaseContext(), AddLocationsActivity.class);
                startActivity(go_to_addLocation_activity);
            }
        });
    }

    public void getData(String location) {
        Log.d(TAG, "getData: " + currentDate.toString());
        String today = currentDate.toString();
        LocalDate futureDate = currentDate.plusDays(5);
        Log.d(TAG, "getData: " + futureDate.toString());
        String lastDate = futureDate.toString();

        HttpRequester requester = new HttpRequester(location, today, lastDate) {
            @Override
            public void UpdateUI() {
                super.UpdateUI();
                try {
                    Location location1;
                    JSONObject jsonObject = new JSONObject(this.response);
                    JSONArray days = jsonObject.getJSONArray("days");
                    //creating the array list of days
                    ArrayList<Day> dayslist = new ArrayList<Day>();
                    for (int i = 0; i < days.length(); i++) {
                        //creating the arraylist of hours
                        JSONObject jsonDay = days.getJSONObject(i);
                        JSONArray jsonHours = jsonDay.getJSONArray("hours");
                        ArrayList<Hour> hoursList = new ArrayList<Hour>();
                        for (int j = 0; j < jsonHours.length(); j++) {
                            JSONObject jsonHour = jsonHours.getJSONObject(j);
                            //creating hour object there will be 24 hour objects for each day object
                            Hour hour = new Hour(
                                    jsonHour.getString("datetime"),
                                    jsonHour.getDouble("temp"),
                                    jsonHour.getDouble("windspeed"),
                                    jsonHour.getDouble("winddir"),
                                    jsonHour.getDouble("cloudcover"),
                                    jsonHour.getString("icon")
                            );
                            hoursList.add(hour);
                        }
                        //creating the days object there will be much objects as specified in the request and each day object has 24 hour objects
                        Day day = new Day(
                                hoursList,
                                jsonDay.getString("datetime"),
                                jsonDay.getDouble("tempmax"),
                                jsonDay.getDouble("tempmin"),
                                jsonDay.getDouble("temp"),
                                jsonDay.getString("sunrise"),
                                jsonDay.getString("sunset"),
                                jsonDay.getString("description")
                        );
                        dayslist.add(day);
                    }
                    //creating the location object each location object will have much days objects as specified in the query they will be as a arraylist of days objects;
                    location1 = new Location("beruwala", location, dayslist);
                    MainActivity.this.locations.add(location1);

                } catch (JSONException e) {
                    Log.e("jsonerror", "UpdateUI: " + e);
                }

                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        HourlyForecastRecyclerAdapter adapter = new HourlyForecastRecyclerAdapter(locations.get(0).getDays().get(0).getHours());
                        hourlyForecastRecycler.setAdapter(adapter);
                        hourlyForecastRecycler.post(() -> {
                            LinearLayoutManager layoutManager = (LinearLayoutManager) hourlyForecastRecycler.getLayoutManager();
                            if (layoutManager != null) {
                                layoutManager.scrollToPositionWithOffset(LocalTime.now().getHour(), 0);
                            }

                        });
                        Log.d(TAG, "run: " + locations.get(0).getDays().get(0).getDate());
                        TextView t = findViewById(R.id.description);
                        t.setText(locations.get(0).getDays().get(0).getDescription());
                        t = findViewById(R.id.tempHour);
                        t.setText(locations.get(0).getDays().get(0).getHours().get(LocalTime.now().getHour()).getTemp() + "°C");
                        t = findViewById(R.id.maxTemp);
                        t.setText(locations.get(0).getDays().get(0).getTempMax() + "°C");
                        t = findViewById(R.id.minTemp);
                        t.setText(locations.get(0).getDays().get(0).getTempMin() + "°C");
                        t = findViewById(R.id.iconDesc);
                        t.setText(locations.get(0).getDays().get(0).getHours().get(LocalTime.now().getHour()).getCloudIcon());
                    }
                });

            }
        };
    }

    public void scrollToPositionOfHourlyRecycler(int position) {
        hourlyForecastRecycler.smoothScrollToPosition(position);
    }
}