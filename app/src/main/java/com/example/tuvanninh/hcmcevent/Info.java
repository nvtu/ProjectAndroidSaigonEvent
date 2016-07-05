package com.example.tuvanninh.hcmcevent;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Tu Van Ninh on 03/07/2016.
 */
public class Info implements Serializable {

    String name, description, phoneNo, address, url;
    int bmpId;

    public Info(String name, String description, String phoneNo, String address, String url, int bmpId) {
        this.name = name;
        this.description = description;
        this.phoneNo = phoneNo;
        this.address = address;
        this.url = url;
        this.bmpId = bmpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBmpId() {
        return bmpId;
    }

    public void setBmpId(int bmpId) {
        this.bmpId = bmpId;
    }
}
