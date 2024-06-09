package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.ResData;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HotelService {
    @GET("/hotels")
    public Call<ResData> getAllHotels();

    // lấy tất cả các phòng theo loại phòng theo 1 khách sạn nào đó
    @GET("/hotels/{id}/rooms")
    public Call<ResData> getByIdRoomClass(@Path("id") int idHotel, @Query("room-class") int idRoomClass);

    // lấy tất cả các phòng theo loại phòng từ ngày bắt đầu đến ngày kết thúc trong thời gian trống
    @GET("/hotels/{idHotel}/rooms/un-booking")
    public Call<ResData> getByIdRoomClassWithStartToEndDate(
            @Path("idHotel") int idHotel,
            @Query("room-class") int idRoomClass,
            @Query("startTime") Date startTime,
            @Query("endTime") Date endTime
    );


    // lấy hạng phòng theo id hotel
    @GET("/hotels/{id}/room-classes")
    public Call<ResData> getListRoomclassByIdHotel(@Path("id") int idHotel);

    // lấy phòng theo id
    @GET("/hotels/{id}")
    public Call<ResData> getHotelByID(@Path("id") int idHotel);



}
