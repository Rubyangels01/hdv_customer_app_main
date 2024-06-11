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
import com.google.gson.internal.LinkedTreeMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                HandlerSelectedStatus(1, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void HandlerSelectedStatus(int user, int status) {
        this.controller.GetListHistoryBooking(user,status);
        binding.tv.setText(Getdataa(this.controller.getObject()));

    }

    public void SetAdapter(ArrayList<BookingItem> list) {
        Toast.makeText(this, "vô 2", Toast.LENGTH_SHORT).show();
        bookingAdapter = new BookingAdapter(this, list);
        binding.lvhistory.setAdapter(bookingAdapter);
    }

    public ArrayList<BookingItem> ConvertList(Object list) {
        // Kiểm tra xem đối tượng "data" có phải là một danh sách không
        if (list instanceof List<?>) {
            // Ép kiểu đối tượng "data" thành danh sách các đối tượng
            List<?> dataList = (List<?>) list;

            // Kiểm tra kiểu dữ liệu của mỗi phần tử trong danh sách
            for (Object obj : dataList) {
                if (obj instanceof Map<?, ?>) {
                    // Chuyển đối tượng Map thành một đối tượng BookingItem
                    Map<?, ?> dataMap = (Map<?, ?>) obj;

                    // Tạo đối tượng BookingItem từ Map
                    BookingItem bookingItem = new BookingItem();
                    double idDouble = (Double) dataMap.get("id");
                    int id = (int) idDouble;
                    bookingItem.setId(id);
                    bookingItem.setCheckin(convertDateString((String) (dataMap.get("checkin"))));
                    bookingItem.setCheckout(convertDateString((String) dataMap.get("checkout")));
                    bookingItem.setPersonName((String) dataMap.get("personName"));
                    double status = (Double) dataMap.get("status");
                    int status1 = (int) status;
                    bookingItem.setId(status1);
//                    bookingItem.setStatus((Integer) dataMap.get("status"));
                    // Thiết lập các thuộc tính khác

                    // Thêm đối tượng BookingItem vào danh sách
                    listbooking.add(bookingItem);
                }
            }
        } else {
            // Xử lý trường hợp khác nếu đối tượng "data" không phải là một danh sách
        }
        return listbooking;

    }

    public String Getdataa(Object ob)
    {

String name = "";
        // In ra thông tin của từng đối tượng trong danh sách

            if (ob instanceof BookingItem) {
                name = ((BookingItem) ob).getPersonName();
                Toast.makeText(this,  ((BookingItem) ob).getPersonName() + "", Toast.LENGTH_SHORT).show();

            } else {
                System.out.println("Không phải BookingItem");
            }
return name;
    }

    public  String convertDateString(String inputDateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(inputDateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }










}