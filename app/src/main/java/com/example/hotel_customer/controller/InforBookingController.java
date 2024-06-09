package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.repositories.BookingRepositoryImpl;
import com.example.hotel_customer.remote.repositories.HotelRepositoryImpl;
import com.example.hotel_customer.view.booking.ChooseBookingActivity;
import com.example.hotel_customer.view.booking.InfoBookingActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforBookingController extends BaseController<InfoBookingActivity, BookingRepositoryImpl> {

    public InforBookingController(InfoBookingActivity view)
    {
        this.view = view;
        this.repository = new BookingRepositoryImpl();
    }
    public void CreateBooking(HotelItemData hotelItemData)
    {
        this.repository.Booking(hotelItemData, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try{
                    if(response.isSuccessful())
                    {
                        ResData resData = response.body();
                        if(resData.getCode() == 200 || resData.getCode() == 201)
                        {
                            view.showNotifyDialog("Đặt phòng thành công");
                        }else
                        {
                            throw new Exception(resData.getMessage());
                        }
                    }
                    else
                    {
                        throw new Exception("Đặt phòng thất bại!");
                    }

                }catch (Exception ex)

                {
                    try {
                        throw new Exception(ex.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {

            }
        });
    }
}
