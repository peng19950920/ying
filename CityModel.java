package com.pengying.citylist;

/**
 * Created by pengying on 2016/10/5.
 */
public class CityModel implements Comparable<CityModel> {
    private String zhName;
    private String enName;
    private double longitude;
    private double latitude;

    public CityModel(String name){
        super();
        this.zhName = name;
        this.enName = name;
        this.longitude = 0;
        this.latitude = 0;
    }

    public CityModel(String zhName,String enName, double longitude, double latitude) {
        this.zhName = zhName;
        this.latitude = latitude;
        this.enName = enName;
        this.longitude = longitude;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public int compareTo(CityModel another) {
        return this.enName.toLowerCase().compareTo(another.getEnName().toLowerCase());
    }

}