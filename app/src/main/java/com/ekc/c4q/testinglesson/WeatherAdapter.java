package com.ekc.c4q.testinglesson;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ekc.c4q.testinglesson.model.Weather;
import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
  private List<Weather> weatherList = new ArrayList<>();

  @Override
  public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
    return new WeatherViewHolder(itemView);
  }

  public void setWeatherList(List<Weather> weatherList) {
    // It's not always the best practice to tell RV that the entire dataset has changed
    // But for the purposes of this exercise, this is good enough

    // You should also think about why we clear the existing list and add the items over
    // Rather than writing:
    // this.weatherList = weatherList;
    // Hint: Try searching "defensive copying" online
    this.weatherList.clear();
    this.weatherList.addAll(weatherList);
    notifyDataSetChanged();
  }

  @Override
  public void onBindViewHolder(WeatherViewHolder holder, int position) {
    Weather weather = weatherList.get(position);
    holder.bind(weather);
  }

  @Override
  public int getItemCount() {
    return weatherList.size();
  }

  public static class WeatherViewHolder extends RecyclerView.ViewHolder {

    private final ImageView icon;
    private final TextView title;
    private final TextView description;

    public WeatherViewHolder(View itemView) {
      super(itemView);
      icon = (ImageView) itemView.findViewById(R.id.icon);
      title = (TextView) itemView.findViewById(R.id.title);
      description = (TextView) itemView.findViewById(R.id.description);
    }

    public void bind(Weather weather) {
      @DrawableRes int imageResource;
      switch (weather.getId()) {
        // Clouds
        case 801:
        case 802:
        case 803:
        case 804:
          imageResource = R.drawable.ic_cloudy;
          break;

        // Rain
        case 500:
        case 501:
          imageResource = R.drawable.ic_rain;
          break;

        // Clear
        case 800:
        default:
          imageResource = R.drawable.ic_clear;
          break;
      }

      icon.setImageResource(imageResource);
      title.setText(weather.getMain());
      description.setText(weather.getDescription());
    }
  }
}
