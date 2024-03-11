package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HourlyForecastRecyclerAdapter extends RecyclerView.Adapter<HourlyForecastRecyclerAdapter.ViewHolder> {
    private static final float FLING_SCALE = 1.0f;
    private ArrayList<Hour> hourlyData;

    public HourlyForecastRecyclerAdapter(ArrayList<Hour> hourlyData) {
        this.hourlyData = hourlyData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.getHour().setText(hourlyData.get(position).getHour());
            holder.getTemperature().setText(String.format("%.1f°C", hourlyData.get(position).getTemp()));
            int imageResource;
            switch (hourlyData.get(position).getCloudIcon()){
                case "clear-day":
                    imageResource=R.drawable.ic_sunny;
                    break;
                case  "snow":
                    imageResource=R.drawable.ic_snow;
                    break;
                case  "rain":
                    imageResource=R.drawable.ic_rain;
                    break;
                case "fog":
                    imageResource=R.drawable.ic_fog;
                    break;
                case "wind":
                    imageResource=R.drawable.ic_wind;
                    break;
                case "cloudy":
                    imageResource=R.drawable.ic_cloudy;
                    break;
                    case "partly-cloudy-day":
                        imageResource=R.drawable.ic_partly_cloudy;
                        break;
                case "partly-cloudy-night":
                    imageResource=R.drawable.ic_partly_cloudy_night;
                    break;
                case "clear-night":
                    imageResource=R.drawable.ic_clear_night;
                    break;
                    default :
                        imageResource=R.drawable.ic_weather_default;
            }
            holder.getIcon().setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return hourlyData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView hour;
        private final TextView temperature;
        private  final ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hour=(TextView) itemView.findViewById(R.id.hourly_forecaset_hour_item);
            temperature=(TextView) itemView.findViewById(R.id.hourly_forecaset_temperature_item);
            icon=(ImageView) itemView.findViewById(R.id.hourly_forecaset_image_item);
        }

        public TextView getHour() {
            return hour;
        }

        public TextView getTemperature() {
            return temperature;
        }

        public ImageView getIcon() {
            return icon;
        }
    }
}
