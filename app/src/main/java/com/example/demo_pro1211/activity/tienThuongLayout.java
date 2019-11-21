package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.dilalog.ThongBaoDiaLog;

public class tienThuongLayout extends AppCompatActivity {
    private TextView tvLevel;
    private Button btnsansang;
    private Dialog dialog;
    private ThongBaoDiaLog thongBaoDiaLog;
    private Button btn_Ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tien_thuong_layout);

        btnsansang = findViewById(R.id.btn_sansang);
        tvLevel = findViewById(R.id.tv_level);
        btn_Ok = findViewById(R.id.btn_ok);
        btnsansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showAlertDialog();


            }
        });

    }
    public void showDialog () {
        dialog = new Dialog(tienThuongLayout.this);

        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }
    public void showAlertDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setMessage("Bạn đã sẵn sàng chưa");

        builder.setPositiveButton("Sẵn sàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(tienThuongLayout.this, PlayerActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void back(View view) {
        Intent intent=new Intent(tienThuongLayout.this,HomeFragment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}




