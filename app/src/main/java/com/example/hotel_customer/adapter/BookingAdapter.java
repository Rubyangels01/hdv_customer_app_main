package com.example.hotel_customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotel_customer.R;
import com.example.hotel_customer.model.BookingItem;
import com.example.hotel_customer.view.booking.BookingActivity;
import com.example.hotel_customer.view.booking.BookingHistoryActivity;

import java.util.List;

public class BookingAdapter extends BaseAdapter {
    private Context context;
    private List<BookingItem> bookingItems;
    BookingHistoryActivity bookinghistory = new BookingHistoryActivity();

    public BookingAdapter(Context context, List<BookingItem> bookingItems) {
        this.context = context;
        this.bookingItems = bookingItems;
    }

    @Override
    public int getCount() {
        return bookingItems.size();
    }

    @Override
    public Object getItem(int position) {
        return bookingItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custome_item_booking, parent, false);
        }

        BookingItem currentItem = bookingItems.get(position);

        TextView idTextView = convertView.findViewById(R.id.tv_id);
        TextView statusTextView = convertView.findViewById(R.id.tv_status);
        TextView checkinTextView = convertView.findViewById(R.id.tv_checkin);
        TextView checkoutTextView = convertView.findViewById(R.id.tv_checkout);
        Button btndetail = convertView.findViewById(R.id.btndetail);


        idTextView.setText("ID: " + currentItem.getId());
        statusTextView.setText("Trạng thái: " + currentItem.getStatus());
        checkinTextView.setText("Checkin: " + currentItem.getCheckinDate());
        checkoutTextView.setText("Checkout: " + currentItem.getCheckoutDate());


        btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
    public void next()
    {

    }




}
