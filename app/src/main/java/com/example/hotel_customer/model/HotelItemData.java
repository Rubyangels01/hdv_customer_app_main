package com.example.hotel_customer.model;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class HotelItemData {
    int id;
    String name;
    float star;
    float minRoomPrice;
    float maxRoomPrice;
    Bitmap image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getStar() {
        return star;
    }

    public float getMinRoomPrice() {
        return minRoomPrice;
    }

    public float getMaxRoomPrice() {
        return maxRoomPrice;
    }

    public Bitmap getImage() {
        return image;
    }

    public HotelItemData(int id, String name, float star, float minRoomPrice, float maxRoomPrice, Bitmap image) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.minRoomPrice = minRoomPrice;
        this.maxRoomPrice = maxRoomPrice;
        this.image = image;
    }
    public HotelItemData() {

    }

}
