package com.whitecode.entity;

import java.util.List;

/**
 * 某地天气详细信息
 * Created by White on 2017/9/29.
 */
public class WeatherDetail {
    private String id;
    private List<Weather2Day> weather2Days;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Weather2Day> getWeather2Days() {
        return weather2Days;
    }

    public void setWeather2Days(List<Weather2Day> weather2Days) {
        this.weather2Days = weather2Days;
    }

    @Override
    public String toString() {
        return "WheatherDetail{" +
                "id=" + id +
                ", weather2Days=" + weather2Days +
                '}';
    }
}
