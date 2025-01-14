package com.example.hotel_customer.view.account;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.RoomClassAdapter;
import com.example.hotel_customer.databinding.ActivityProfileBinding;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.booking.BookingHistoryActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding binding;
    Dialog dialogChooseEditInfo, dialogComfirmLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setEvent();
    }

    private void setEvent() {
        binding.btnHistory.setOnClickListener(v -> {
            Intent history = new Intent(this, BookingHistoryActivity.class);
            startActivity(history);
        });
        binding.btnEditInfo.setOnClickListener(v -> {
            showDialogChooseEditInfo();
        });
        binding.btnLogout.setOnClickListener(v -> {
            showDialogComfirmLogout();
        });
    }

    private void showDialogComfirmLogout() {
        if (dialogComfirmLogout == null) {
            dialogComfirmLogout = new Dialog(this);

            dialogComfirmLogout.setContentView(R.layout.dialog_comfirm);
            dialogComfirmLogout.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialogComfirmLogout.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView tvContent =  dialogComfirmLogout.findViewById(R.id.tvContent);
            tvContent.setText("bạn có muốn đăng xuất?");

            dialogComfirmLogout.findViewById(R.id.btnComfirm).setOnClickListener(v -> {
                handleLogout();
                dialogComfirmLogout.cancel();
            });
            
            dialogComfirmLogout.findViewById(R.id.btnCancle).setOnClickListener(v -> {
                dialogComfirmLogout.cancel();
            });
        }

        dialogComfirmLogout.show();
    }

    private void handleLogout() {
    }

    private void showDialogChooseEditInfo() {
        if (dialogChooseEditInfo == null) {
            dialogChooseEditInfo = new Dialog(this);

            dialogChooseEditInfo.setContentView(R.layout.dialog_choose_edit_info);
            dialogChooseEditInfo.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialogChooseEditInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialogChooseEditInfo.findViewById(R.id.btnCancle).setOnClickListener(v -> {
                dialogChooseEditInfo.cancel();
            });

            dialogChooseEditInfo.findViewById(R.id.editAccount).setOnClickListener(v -> {
                handleEditAccount();
                dialogChooseEditInfo.cancel();
            });

            dialogChooseEditInfo.findViewById(R.id.editAvatar).setOnClickListener(v -> {
                handleEditAvatar();
                dialogChooseEditInfo.cancel();
            });
        }

        dialogChooseEditInfo.show();
    }

    private void handleEditAvatar() {
        Intent editAvatar = new Intent(this, EditAvatarActivity.class);
        startActivity(editAvatar);
    }

    private void handleEditAccount() {
        Intent editAccount = new Intent(this, EditAccountActivity.class);
        startActivity(editAccount);
    }
}