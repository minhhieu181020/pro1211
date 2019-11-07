package com.example.demo_pro1211;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeFragment extends AppCompatActivity {
    private ImageView imgabout, imgsetting, imghighscore;
    private AlertDialog alertDialog;
    private ImageButton imgPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        imghighscore = findViewById(R.id.btn_high_score);
        imgabout = findViewById(R.id.btn_about);
        imgsetting = findViewById(R.id.btn_setting);
        imgPlay = findViewById(R.id.btn_play);


        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this, tienThuongLayout.class);
                startActivity(intent);
            }
        });
        imgabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeFragment.this);
                alertDialog.setView(R.layout.thongtin_dialog);
                alertDialog.show();
            }
        });
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this, settingActivity.class);
                startActivity(intent);

            }
        });
        imghighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this, HighScoreActivity.class);
                startActivity(intent);
            }
        });
    }


}
