package com.training.android.roomate.Model;

import java.util.HashMap;

/**
 * Created by Dyste on 10/8/2017.
 */

public class ApartmentModel {

    private String Name;
    private String Address;
    private String City;
    private String RMNeeded;
    private HashMap<String,String> apartment_tenants;

    public ApartmentModel() {
    }

    public ApartmentModel(String name, String address, String city, String RMNeeded, HashMap<String, String> apartment_tenants) {
        Name = name;
        Address = address;
        City = city;
        this.RMNeeded = RMNeeded;
        this.apartment_tenants = apartment_tenants;
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

    public HashMap<String, String> getApartment_tenants() {
        return apartment_tenants;
    }

    public void setApartment_tenants(HashMap<String, String> apartment_tenants) {
        this.apartment_tenants = apartment_tenants;
    }
}
