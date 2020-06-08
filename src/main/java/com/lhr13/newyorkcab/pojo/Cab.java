package com.lhr13.newyorkcab.pojo;

public class Cab {
    private String medallion;
    private String hack_license;
    private String vendor_id;
    private String rate_code;
    private String store_and_fwd_flag;
    private String pickup_datatime;
    private String dropoff_datatime;
    private String passenger_count;
    private String trip_time_in_secs;
    private String trip_distance;
    private String pickup_longitude;
    private String pickup_latitude;
    private String dropoff_longitude;
    private String dropoff_latitude;

    public Cab() {
    }

    @Override
    public String toString() {
        return "Cab{" +
                "medallion='" + medallion + '\'' +
                ", hack_license='" + hack_license + '\'' +
                ", vendor_id='" + vendor_id + '\'' +
                ", rate_code='" + rate_code + '\'' +
                ", store_and_fwd_flag='" + store_and_fwd_flag + '\'' +
                ", pickup_datatime='" + pickup_datatime + '\'' +
                ", dropoff_datatime='" + dropoff_datatime + '\'' +
                ", passenger_count='" + passenger_count + '\'' +
                ", trip_time_in_secs='" + trip_time_in_secs + '\'' +
                ", trip_distance='" + trip_distance + '\'' +
                ", pickup_longitude='" + pickup_longitude + '\'' +
                ", pickup_latitude='" + pickup_latitude + '\'' +
                ", dropoff_longitude='" + dropoff_longitude + '\'' +
                ", dropoff_latitude='" + dropoff_latitude + '\'' +
                '}';
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getHack_license() {
        return hack_license;
    }

    public void setHack_license(String hack_license) {
        this.hack_license = hack_license;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getRate_code() {
        return rate_code;
    }

    public void setRate_code(String rate_code) {
        this.rate_code = rate_code;
    }

    public String getStore_and_fwd_flag() {
        return store_and_fwd_flag;
    }

    public void setStore_and_fwd_flag(String store_and_fwd_flag) {
        this.store_and_fwd_flag = store_and_fwd_flag;
    }

    public String getPickup_datatime() {
        return pickup_datatime;
    }

    public void setPickup_datatime(String pickup_datatime) {
        this.pickup_datatime = pickup_datatime;
    }

    public String getDropoff_datatime() {
        return dropoff_datatime;
    }

    public void setDropoff_datatime(String dropoff_datatime) {
        this.dropoff_datatime = dropoff_datatime;
    }

    public String getPassenger_count() {
        return passenger_count;
    }

    public void setPassenger_count(String passenger_count) {
        this.passenger_count = passenger_count;
    }

    public String getTrip_time_in_secs() {
        return trip_time_in_secs;
    }

    public void setTrip_time_in_secs(String trip_time_in_secs) {
        this.trip_time_in_secs = trip_time_in_secs;
    }

    public String getTrip_distance() {
        return trip_distance;
    }

    public void setTrip_distance(String trip_distance) {
        this.trip_distance = trip_distance;
    }

    public String getPickup_longitude() {
        return pickup_longitude;
    }

    public void setPickup_longitude(String pickup_longitude) {
        this.pickup_longitude = pickup_longitude;
    }

    public String getPickup_latitude() {
        return pickup_latitude;
    }

    public void setPickup_latitude(String pickup_latitude) {
        this.pickup_latitude = pickup_latitude;
    }

    public String getDropoff_longitude() {
        return dropoff_longitude;
    }

    public void setDropoff_longitude(String dropoff_longitude) {
        this.dropoff_longitude = dropoff_longitude;
    }

    public String getDropoff_latitude() {
        return dropoff_latitude;
    }

    public void setDropoff_latitude(String dropoff_latitude) {
        this.dropoff_latitude = dropoff_latitude;
    }

    public Cab(String medallion, String hack_license,
               String vendor_id, String rate_code,
               String store_and_fwd_flag, String pickup_datatime,
               String dropoff_datatime, String passenger_count,
               String trip_time_in_secs, String trip_distance,
               String pickup_longitude, String pickup_latitude,
               String dropoff_longitude, String dropoff_latitude) {
        this.medallion = medallion;
        this.hack_license = hack_license;
        this.vendor_id = vendor_id;
        this.rate_code = rate_code;
        this.store_and_fwd_flag = store_and_fwd_flag;
        this.pickup_datatime = pickup_datatime;
        this.dropoff_datatime = dropoff_datatime;
        this.passenger_count = passenger_count;
        this.trip_time_in_secs = trip_time_in_secs;
        this.trip_distance = trip_distance;
        this.pickup_longitude = pickup_longitude;
        this.pickup_latitude = pickup_latitude;
        this.dropoff_longitude = dropoff_longitude;
        this.dropoff_latitude = dropoff_latitude;
    }
}
