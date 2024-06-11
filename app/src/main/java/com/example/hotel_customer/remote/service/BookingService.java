package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.model.ResData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingService {
    @POST("/bookings")
    public Call<ResData> createBooking(@Body HotelItemData hotelItemData);


    @GET("/users/{idUser}/bookings")
    public Call<ResData> getHistoryBooking(
            @Path("idUser") int idUser,
            @Query("status") int bookingStatus
    );

    @GET("/users/{idUser}/bookings")
    public Call<ResData> getHistoryBooking2(
            @Path("idUser") int idUser,
            @Query("status") int bookingStatus
    );



    @GET("/bookings/{idBooking}")
    public Call<ResData> getBookingByID(@Path("idBooking") int idBooking);



}
