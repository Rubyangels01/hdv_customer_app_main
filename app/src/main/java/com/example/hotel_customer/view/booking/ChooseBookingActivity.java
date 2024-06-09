package com.example.hotel_customer.view.booking;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.RoomClassAdapter;
import com.example.hotel_customer.controller.HotelController;
import com.example.hotel_customer.databinding.ActivityChooseBookingBinding;
import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChooseBookingActivity extends BaseActivity<HotelController> {
    ActivityChooseBookingBinding binding;
    Dialog dialogChoooseRoom;
    List<RoomClass> roomClasses;
    RoomClassAdapter roomClassAdapter;
    List<HotelItemData> listroomchoosed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityChooseBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.controller = new HotelController(this);
        listroomchoosed = new ArrayList<>();

        setEvent();
    }

    private void setEvent() {
        binding.btnAddRoom.setOnClickListener(v -> {
            showDialogSelectRoom();
        });
        
        binding.btnNext.setOnClickListener(v -> {
            handleNext();
        });

        // Set event click button ic_date
        binding.icDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectStartDay();
            }
        });

        // button checkout
        binding.icDateCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDateOut();
            }
        });
    }

    private void SelectDateOut() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseBookingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        // Ví dụ: hiển thị ngày đã chọn lên một TextView
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        binding.tvCheckout.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    private void SelectStartDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseBookingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        // Ví dụ: hiển thị ngày đã chọn lên một TextView
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        binding.tvDay.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    private void handleNext() {
        Intent intent = new Intent(this, InfoBookingActivity.class);
        startActivity(intent);
    }

    private void showDialogSelectRoom() {
        showWaitingDialog();
        if(dialogChoooseRoom == null){
            dialogChoooseRoom= new Dialog(this);
            dialogChoooseRoom.setContentView(R.layout.dialog_choose_room);

            dialogChoooseRoom.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialogChoooseRoom.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_fill_rounded_smoke));

            Spinner spinner = dialogChoooseRoom.findViewById(R.id.spLoaiPhong);
            roomClasses = new ArrayList<>();
            roomClassAdapter = new RoomClassAdapter( roomClasses);
            spinner.setAdapter(roomClassAdapter);

            dialogChoooseRoom.findViewById(R.id.btnAddRoom).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandleChooseroom();
                }
            });

            dialogChoooseRoom.findViewById(R.id.btnClose).setOnClickListener(v -> {
                dialogChoooseRoom.cancel();
            });
        }
        updateRoomClassesData();
    }

    private void HandleChooseroom() {
        this.controller.GetHotelByID(123);
    }


    private void updateRoomClassesData() {
        // gọi api lấy dữ liệu room class GET: /hotels/{idHotel}/room-classes
        // sau khi gọi api thành công thì cập nhập data cho room classes và notify change adapter

        // data giả
        roomClasses.clear();

        this.controller.getListRoomclassByIdHotel1(123);

        roomClasses.add(RoomClass.builder().id(1).name("single").build());
        roomClasses.add(RoomClass.builder().id(2).name("single queen").build());
        roomClasses.add(RoomClass.builder().id(3).name("double").build());
        roomClasses.add(RoomClass.builder().id(4).name("double queen").build());
        roomClassAdapter.notifyDataSetChanged();

        dialogChoooseRoom.show();
        cancleWaitingDialog();
    }
    // Tạo hàm set sự kiện khi click btnAddroom
    private void SetChooseRoom()
    {



    }
    public void ChooseRoom(HotelItemData hotelItemData)
    {
        listroomchoosed.add(hotelItemData);
    }
    public HotelItemData ConvertObjectToHotelData(Object hotel) {
        if (hotel instanceof HotelItemData) {
            return (HotelItemData) hotel;
        } else {
            throw new IllegalArgumentException("The provided object is not of type HotelItemData.");
        }
    }


    public void listRoomClass(Object list) {
        if (list instanceof List<?>) {
            try {
                List<?> genericList = (List<?>) list;
                for (Object item : genericList) {
                    if (item instanceof RoomClass) {
                        roomClasses.add((RoomClass) item);
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

    }

}