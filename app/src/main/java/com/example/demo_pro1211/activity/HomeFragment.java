package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.demo_pro1211.R;

public class HomeFragment extends AppCompatActivity {
    private ImageView imgabout, imgsetting, imghighscore;
    private AlertDialog alertDialog;
    private ImageButton imgPlay;
    private View imgcircle;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        imghighscore = findViewById(R.id.btn_high_score);
        imgabout = findViewById(R.id.btn_about);
        imgsetting = findViewById(R.id.btn_setting);
        imgPlay = findViewById(R.id.btn_play);
        imgcircle=findViewById(R.id.bg_circle_anim);
        mediaPlayer=MediaPlayer.create(this,R.raw.hihi);
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imgcircle,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
//        mediaPlayer.start();
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeFragment.this, tienThuongLayout.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
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
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });
        imghighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this, HighScoreActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
    }


}
