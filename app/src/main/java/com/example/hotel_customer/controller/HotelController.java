package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.repositories.HotelRepositoryImpl;
import com.example.hotel_customer.view.booking.ChooseBookingActivity;
import com.example.hotel_customer.view.hotel.HotelActivity;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelController extends BaseController<ChooseBookingActivity, HotelRepositoryImpl> {
    public HotelController(ChooseBookingActivity view)
    {
        this.view = view;
        this.repository = new HotelRepositoryImpl();
    }
    public void bookingHotel()
    {
        this.repository.GetAllHotel(new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try{
                    if(response.isSuccessful())
                    {
                        ResData resData = response.body();
                        if(resData.getCode() == 200 || resData.getCode() == 201)
                        {
                            view.showNotifyDialog("call Api thành công");
                        }else
                        {
                            throw new Exception(resData.getMessage());
                        }
                    }
                    else
                    {
                        throw new Exception("Truy xuất dữ liệu thất bại");
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
    public void GetHotelByType(int idHotel, int idRoomClass, Date startTime, Date endTime) {
        this.repository.getByIdRoomClassWithStartToEndDate(idHotel, idRoomClass,startTime,endTime, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try {
                    if (response.isSuccessful()) {
                        ResData resData = response.body();
                        if (resData != null && (resData.getCode() == 200 || resData.getCode() == 201)) {
                            view.showNotifyDialog("Gọi API thành công");
                        } else {
                            throw new Exception(resData != null ? resData.getMessage() : "Không có dữ liệu trả về từ máy chủ");
                        }
                    } else {
                        throw new Exception("Truy xuất dữ liệu thất bại, mã lỗi: " + response.message());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    public void getListRoomclassByIdHotel1(int idHotel) {
        this.repository.getListRoomclassByIdHotel(idHotel, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try {
                    if (response.isSuccessful()) {
                        ResData resData = response.body();
                        if (resData != null && (resData.getCode() == 200 || resData.getCode() == 201)) {
                            view.listRoomClass(resData.getData());
                        } else {
                            throw new Exception(resData != null ? resData.getMessage() : "Không có dữ liệu trả về từ máy chủ");
                        }
                    } else {
                        throw new Exception("Truy xuất dữ liệu thất bại, mã lỗi: " + response.message());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {

            }
        });
    }

    public void GetHotelByID(int idHotel) {
        this.repository.getHotelByID(idHotel, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try {
                    if (response.isSuccessful()) {
                        ResData resData = response.body();
                        if (resData != null && (resData.getCode() == 200 || resData.getCode() == 201)) {
                            view.ChooseRoom(view.ConvertObjectToHotelData(resData.getData()));
                        } else {
                            throw new Exception(resData != null ? resData.getMessage() : "Không có dữ liệu trả về từ máy chủ");
                        }
                    } else {
                        throw new Exception("Truy xuất dữ liệu thất bại, mã lỗi: " + response.message());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {

            }
        });
    }





}
