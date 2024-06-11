package com.example.hotel_customer.remote.repositories.interfaces;

import com.example.hotel_customer.remote.repositories.callback.RepositoryCallback;

public interface BookingHistoryRepository extends Repository{
    public void getHistoryBooking(int idUser, int status, RepositoryCallback callback);
}
