package com.example.hotel_customer.remote.data;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RoomClass {
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoomClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomClass() {

    }
    public static class Builder {
        private int id;
        private String name;

        public Builder() {
        }

        public RoomClass.Builder id(int id) {
            this.id = id;
            return this;
        }

        public RoomClass.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RoomClass build() {
            return new RoomClass(id, name);
        }
    }

    // Static method to get a new Builder instance
    public static RoomClass.Builder builder() {
        return new RoomClass.Builder();
    }
}
