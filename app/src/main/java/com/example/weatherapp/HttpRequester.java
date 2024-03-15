package com.example.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

//i need to make this class implement like


public class HttpRequester {
    public String LOCATION;
    private static final String TAG = "HttpRequesterError";

    public String response;

    public HttpRequester(String location,String firstDate,String lastDate) {
        LOCATION = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + location +"/"+firstDate+"/"+lastDate+"?unitGroup=metric&key=C3P9C57YN5L2DDXRKRZ5KQFJ8&contentType=json";
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Workertasks());
    }

    class Workertasks implements Runnable {
        @Override
        public void run() {
            int responseCode = 0;
            try {
                URL url = new URL(LOCATION);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                responseCode = connection.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                HttpRequester.this.response = response.toString();
                UpdateUI();
            } catch (Exception e) {
                Log.e(TAG, "sendREquest: " + responseCode, e);
            }
        }
    }
    public  void UpdateUI() {
        //this method sholud be overided in imlementation in another class and do the ui updates accordingly
        Log.d(TAG, "UpdateUI: " + response);
    }

}
