package com.ekc.c4q.testinglesson;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ekc.c4q.testinglesson.WeatherAdapter.WeatherViewHolder;
import com.ekc.c4q.testinglesson.model.Weather;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WeatherViewHolderTest {

  @Mock ImageView icon;
  @Mock TextView title;
  @Mock TextView description;
  @Mock ViewGroup viewGroup;

  private WeatherViewHolder weatherViewHolder;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    when(viewGroup.findViewById(R.id.icon)).thenReturn(icon);
    when(viewGroup.findViewById(R.id.title)).thenReturn(title);
    when(viewGroup.findViewById(R.id.description)).thenReturn(description);

    weatherViewHolder = new WeatherViewHolder(viewGroup);
  }

  @Test
  public void ctor_findsViewsById() {
    verify(viewGroup).findViewById(R.id.icon);
    verify(viewGroup).findViewById(R.id.title);
    verify(viewGroup).findViewById(R.id.description);
  }

  @Test
  public void bind_bindsWeatherToViews() {
    // Given
    Weather weather = mock(Weather.class);
    when(weather.getMain()).thenReturn("title");
    when(weather.getDescription()).thenReturn("description");
    when(weather.getId()).thenReturn(804);

    // When
    weatherViewHolder.bind(weather);

    // Then
    verify(icon).setImageResource(R.drawable.ic_cloudy);
    verify(title).setText("title");
    verify(description).setText("description");
  }
}