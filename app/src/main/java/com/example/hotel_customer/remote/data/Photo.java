package com.example.hotel_customer.remote.data;

import android.graphics.Bitmap;

public class Photo {
    private int id;
    private Bitmap bitmap;

    // No-argument constructor
    public Photo() {
    }

    // All-argument constructor
    public Photo(int id, Bitmap bitmap) {
        this.id = id;
        this.bitmap = bitmap;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for bitmap
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    // toString method
    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", bitmap=" + bitmap +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != photo.id) return false;
        return bitmap != null ? bitmap.equals(photo.bitmap) : photo.bitmap == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bitmap != null ? bitmap.hashCode() : 0);
        return result;
    }

    // Static Builder class
    public static class Builder {
        private int id;
        private Bitmap bitmap;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder bitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Photo build() {
            return new Photo(id, bitmap);
        }
    }

    // Static method to get a new Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
