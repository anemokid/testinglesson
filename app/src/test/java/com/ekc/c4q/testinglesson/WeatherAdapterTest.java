package com.ekc.c4q.testinglesson;

import android.widget.FrameLayout;
import com.ekc.c4q.testinglesson.WeatherAdapter.WeatherViewHolder;
import com.ekc.c4q.testinglesson.model.Weather;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WeatherAdapterTest {

  @Mock Weather weather;

  private WeatherAdapter adapter;

  @Before
  public void setUp() {
    // This just calls mock(Class) for every field annotated with @Mock
    MockitoAnnotations.initMocks(this);

    adapter = new WeatherAdapter();
  }

  @Test
  public void onCreateViewHolder_createsViewHolder() {
    WeatherViewHolder viewHolder =
        adapter.onCreateViewHolder(new FrameLayout(RuntimeEnvironment.application), 0);

    assertThat(viewHolder).isNotNull();
    assertThat(viewHolder).isInstanceOf(WeatherViewHolder.class);
  }

  @Test
  public void onBindViewHolder_bindViewHolder() {
    // Given
    adapter.setWeatherList(Collections.singletonList(weather));

    // When
    WeatherViewHolder viewHolder = mock(WeatherViewHolder.class);
    adapter.onBindViewHolder(viewHolder, 0);

    // Then
    verify(viewHolder).bind(weather);
    // Note that we don't check if the view holder binded correctly
    // Remember the scope of your system under test: verify that your methods call the right methods
    // Whether or not viewHolder binds weathers correctly should be tested by ViewHolderTest, not
    // Adapter
  }

  @Test
  public void getItemCount_whenSetWeatherList_returnsSameListSize() {
    // Given
    assertThat(adapter.getItemCount()).isEqualTo(0);

    // When
    adapter.setWeatherList(Arrays.asList(mock(Weather.class),
        mock(Weather.class),
        mock(Weather.class),
        mock(Weather.class)));

    // Then
    assertThat(adapter.getItemCount()).isEqualTo(4);
  }

  @Test
  public void setWeatherList_notifiesDatasetChanged() {
    // Given
    // An example using spy, which lets you call verify on real objects
    // Spy generally implies you've written untestable code! Sometimes unavoidable without
    // excessive abstraction.
    WeatherAdapter spy = spy(adapter);

    // When
    spy.setWeatherList(Arrays.asList(mock(Weather.class),
        mock(Weather.class),
        mock(Weather.class),
        mock(Weather.class)));

    // Then
    verify(spy).notifyDataSetChanged();
  }
}
