package com.training.android.roomate.Model;

/**
 * Created by Dyste on 10/8/2017.
 */

public class ApartmentModel {

    private String Name;
    private String Address;
    private String City;
    private String RMNeeded;

    public ApartmentModel() {
    }

    public ApartmentModel(String name, String address, String city, String RMNeeded) {
        Name = name;
        Address = address;
        City = city;
        this.RMNeeded = RMNeeded;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRMNeeded() {
        return RMNeeded;
    }

    public void setRMNeeded(String RMNeeded) {
        this.RMNeeded = RMNeeded;
    }
}
