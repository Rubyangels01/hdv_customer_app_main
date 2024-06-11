package com.example.hotel_customer.model;

public class    BookingItem {
    private int id;
    private int status;
    private String checkin;
    private String checkout;



    public BookingItem(int id, int status, String checkin, String checkout) {
        this.id = id;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheckinDate() {
        return checkin;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkin = checkinDate;
    }

    public String getCheckoutDate() {
        return checkout;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkout = checkoutDate;
    }




}

