package com.example.hotel_customer.model;

public class    BookingItem {
    private int id;
    private int status;
    private String checkin;
    private String checkout;
    private String note;
    private String personName;
    private String personPhone;
    private int idUser;
    public BookingItem(){}

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public BookingItem(int id, int status, String checkin, String checkout, String note, String personName, String personPhone, int idUser) {
        this.id = id;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.note = note;
        this.personName = personName;
        this.personPhone = personPhone;
        this.idUser = idUser;
    }



    public String getNote() {
        return note;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public int getIdUser() {
        return idUser;
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

