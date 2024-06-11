package com.example.hotel_customer.controller;

import android.widget.Toast;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.repositories.BookingHistoryRepositoryImpl;
import com.example.hotel_customer.remote.repositories.BookingRepositoryImpl;
import com.example.hotel_customer.view.booking.BookingHistoryActivity;
import com.example.hotel_customer.view.booking.InfoBookingActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryController extends BaseController<BookingHistoryActivity, BookingRepositoryImpl> {
    public BookingHistoryController(BookingHistoryActivity view)
    {
        this.view = view;
        this.repository = new BookingRepositoryImpl();
    }
    public static int code = 0;
    public void GetListHistoryBooking(int idUser, int status)
    {
        code =1;
        this.repository.getHistoryBooking2(idUser, status, new Callback<ResData>() {

            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try{
                    code = 2;
                    if(response.isSuccessful())
                    {
                        code =3;
                        ResData resData = response.body();
                        if(resData.getCode() == 200 || resData.getCode() == 201)
                        {
                            code =4;
                             code = resData.getCode();
//                            view.SetAdapter(view.ConvertList(resData.getData()));
                        }else
                        {
                            code = 5;
                            throw new Exception(resData.getMessage());
                        }
                    }
                    else
                    {
                        code = 6;
                        throw new Exception("Đặt phòng thất bại!");
                    }

                }catch (Exception ex)

                {
                    try {
                        code = 7;
                        throw new Exception(ex.getMessage());
                    } catch (Exception e) {
                        code = 8;
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                code = 9;
            }
        });
    }
    public int getMa()
    {
        return code;
    }
}
