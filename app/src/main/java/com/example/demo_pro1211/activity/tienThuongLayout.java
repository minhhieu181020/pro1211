package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.dilalog.ThongBaoDiaLog;
import com.example.demo_pro1211.interfaces.TienThuongView;
import com.example.demo_pro1211.presenter.TienThuongPresenter;

public class tienThuongLayout extends AppCompatActivity implements TienThuongView  {

    private Button btnsansang;
    private Dialog dialog;
    private ImageView imgback;
    private TienThuongPresenter tienThuongPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tien_thuong_layout);
        imgback=findViewById(R.id.imgback);
        btnsansang = findViewById(R.id.btn_sansang);
        tienThuongPresenter=new TienThuongPresenter(this);

        btnsansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tienThuongPresenter.showAlertDialog();


            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tienThuongPresenter.back();
            }
        });

    }


    @Override
    public void back() {
        Intent intent = new Intent(tienThuongLayout.this, HomeFragment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void ShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Bạn đã sẵn sàng chưa");

        builder.setPositiveButton("Sẵn sàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(tienThuongLayout.this, PlayerActivity.class);
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

    @Override
    public void ShowDialog() {
        dialog = new Dialog(tienThuongLayout.this);

        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }
}




