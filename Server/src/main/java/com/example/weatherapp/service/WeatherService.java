package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.model.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {
    private final Map<String, WeatherData> weatherDataMap = new ConcurrentHashMap<>();

    public void updateWeather(String city, double temperature, double humidity) {
        weatherDataMap.put(city, new WeatherData(city, temperature, humidity));
    }

    public WeatherResponse getWeather(String city) {
        WeatherData data = weatherDataMap.get(city);
        if (data != null) {
            return new WeatherResponse(city, data.getTemperature(), data.getHumidity());
        }
        return new WeatherResponse("No data available");
    }
}
