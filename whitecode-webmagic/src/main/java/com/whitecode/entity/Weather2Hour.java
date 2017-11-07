package com.whitecode.entity;

/**
 * 一天中各个时间段的天气
 * Created by White on 2017/9/29.
 */
public class Weather2Hour {
    // 时间
    private String hour;
    // 天气情况
    private String wea;
    // 温度
    private String temperature;
    // 风强度
    private String windStrength;
    // 风级数
    private String series;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindStrength() {
        return windStrength;
    }

    public void setWindStrength(String windStrength) {
        this.windStrength = windStrength;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Weather2Hour{" +
                "hour='" + hour + '\'' +
                ", wea='" + wea + '\'' +
                ", temperature='" + temperature + '\'' +
                ", windStrength='" + windStrength + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
