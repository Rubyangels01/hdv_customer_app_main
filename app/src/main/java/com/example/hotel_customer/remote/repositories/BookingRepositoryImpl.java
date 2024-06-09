package com.example.hotel_customer.remote.repositories;

import com.example.hotel_customer.controller.InforBookingController;
import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.BookingService;
import com.example.hotel_customer.remote.service.HotelService;

import retrofit2.Callback;

public class BookingRepositoryImpl implements Repository {
    BookingService bookingService;
    public BookingRepositoryImpl() {
        bookingService = RetrofitClient.gI().getRetrofit().create(BookingService.class);
    }

    public void Booking(HotelItemData hotelItemData, Callback<ResData> callback)
    {
        bookingService.createBooking(hotelItemData).enqueue(callback);
    }

    public void getHistoryBooking(int IdUser,int status, Callback<ResData> callback)
    {
        bookingService.getHistoryBooking(IdUser,status).enqueue(callback);
    }
    public void getBookingByID(int idBooking, Callback<ResData> callback)
    {
        bookingService.getBookingByID(idBooking).enqueue(callback);
    }


}
