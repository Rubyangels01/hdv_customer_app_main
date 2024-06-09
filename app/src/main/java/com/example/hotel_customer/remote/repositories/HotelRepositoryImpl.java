package com.example.hotel_customer.remote.repositories;

import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.BookingService;
import com.example.hotel_customer.remote.service.HotelService;

import java.util.Date;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class HotelRepositoryImpl implements Repository {
    HotelService hotelService;

    public HotelRepositoryImpl() {
        hotelService = RetrofitClient.gI().getRetrofit().create(HotelService.class);

    }
    public void GetAllHotel(Callback<ResData> callback)
    {
        hotelService.getAllHotels().enqueue(callback);

    }
    public void getByIdRoomClassWithStartToEndDate(int idHotel, int idRoomClass, Date startTime,Date endTime, Callback<ResData> callback) {
        hotelService.getByIdRoomClassWithStartToEndDate(idHotel, idRoomClass,startTime,endTime).enqueue(callback);
    }

    public void getListRoomclassByIdHotel(int idHotel, Callback<ResData> callback) {
        hotelService.getListRoomclassByIdHotel(idHotel).enqueue(callback);
    }

    public void getHotelByID(int idHotel, Callback<ResData> callback) {
        hotelService.getHotelByID(idHotel).enqueue(callback);
    }


}
