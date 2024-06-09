package com.example.hotel_customer.model;

import android.graphics.Bitmap;

import com.example.hotel_customer.remote.data.Photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BookingStatus implements AdapterModel{
    int id;
    String name;

    public BookingStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookingStatus() {

    }


    @Override
    public String getContent() {
        return name;
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder() {
        }

        public BookingStatus.Builder id(int id) {
            this.id = id;
            return this;
        }

        public BookingStatus.Builder name(String name) {
            this.name = name;
            return this;
        }

        public BookingStatus build() {
            return new BookingStatus(id, name);
        }
    }

    // Static method to get a new Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
