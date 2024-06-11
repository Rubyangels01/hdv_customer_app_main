package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.BookingAdapter;
import com.example.hotel_customer.adapter.SpinnerAdapter;
import com.example.hotel_customer.controller.BookingHistoryController;
import com.example.hotel_customer.databinding.ActivityBookingHistoryBinding;
import com.example.hotel_customer.model.AdapterModel;
import com.example.hotel_customer.model.BookingItem;
import com.example.hotel_customer.model.BookingStatus;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends BaseActivity<BookingHistoryController> {
    ActivityBookingHistoryBinding binding;
    SpinnerAdapter spinnerAdapter;
    ArrayList<BookingItem> listbooking;

    List<BookingStatus> statuses;
    BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBookingHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listbooking = new ArrayList<>();
        BookingItem b1 = new BookingItem(1, 1,"17/1/2024","19/1/2024");
        listbooking.add(b1);
        this.controller = new BookingHistoryController(this);
        initUI();
        setEvent();
//        showWaitingDialog("đang đăng nhập");

    }

    private void initUI() {
        statuses = new ArrayList<>();
        statuses.add(BookingStatus.builder().id(1).name("chưa thanh toán").build());
        statuses.add(BookingStatus.builder().id(2).name("đã thanh toán").build());
        statuses.add(BookingStatus.builder().id(3).name("checkin").build());
        statuses.add(BookingStatus.builder().id(4).name("checkout").build());
        statuses.add(BookingStatus.builder().id(5).name("xong").build());
        statuses.add(BookingStatus.builder().id(6).name("đã hủy").build());
        spinnerAdapter = new SpinnerAdapter(statuses);
        binding.spStatus.setAdapter(spinnerAdapter);
    }

    private void setEvent() {
        binding.getRoot().setOnClickListener(v -> {
            Intent booking = new Intent(this, BookingActivity.class);
            startActivity(booking);
        });
        binding.spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingStatus selectedStatus = (BookingStatus) parent.getItemAtPosition(position);
                int selectedID = selectedStatus.getId();
                Toast.makeText(BookingHistoryActivity.this, selectedID+"", Toast.LENGTH_SHORT).show();

                HandlerSelectedStatus(1, 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void HandlerSelectedStatus(int user, int status) {
        this.controller.GetListHistoryBooking(1,1);
        binding.tv.setText(this.controller.getMa() + "");
//        bookingAdapter = new BookingAdapter(this, listbooking);
//        binding.lvhistory.setAdapter(bookingAdapter);

    }

    public void SetAdapter(ArrayList<BookingItem> list) {
        Toast.makeText(this, "vô 2", Toast.LENGTH_SHORT).show();
        bookingAdapter = new BookingAdapter(this, list);
        binding.lvhistory.setAdapter(bookingAdapter);
    }

    public ArrayList<BookingItem> ConvertList(Object list) {

        if (list instanceof List<?>) {
            try {
                List<?> genericList = (List<?>) list;
                for (Object item : genericList) {
                    if (item instanceof BookingItem) {
                        listbooking.add((BookingItem) item);
                    } else {
                        throw new IllegalArgumentException("The list contains an item that is not of type RoomClass.");
                    }
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("The provided object is not a list of RoomClass objects.", e);
            }
        } else {
            throw new IllegalArgumentException("The provided object is not a list.");
        }
        return listbooking;
    }




}