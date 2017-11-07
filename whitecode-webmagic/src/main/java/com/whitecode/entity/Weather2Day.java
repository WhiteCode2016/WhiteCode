package com.whitecode.entity;

/**
 * 每天的天气情况
 * Created by White on 2017/9/29.
 */
public class Weather2Day {
    // 日期
    private String dateTime;
    // 天气情况
    private String wea;
    // 最高温度
    private String maxTemperature;
    // 最低温度
    private String minTemperature;
    // 风级数
    private String series;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Weather2Day{" +
                "dateTime='" + dateTime + '\'' +
                ", wea='" + wea + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
