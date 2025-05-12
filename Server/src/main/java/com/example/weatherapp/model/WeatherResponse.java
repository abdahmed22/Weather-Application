package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherResponse {
    private String city;
    private Double temperature;
    private Double humidity;
    private String message;

    public WeatherResponse(String city, Double temperature, Double humidity) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public WeatherResponse(String message) {
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getMessage() {
        return message;
    }
}
