package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.databinding.HomeFragmentBinding;
import com.example.demo_pro1211.interfaces.HomeView;
import com.example.demo_pro1211.presenter.HomePresenter;

public class HomeFragment extends AppCompatActivity implements HomeView {

    private Button btnDong;

    private View imgcircle;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeFragmentBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.home_fragment);

        imgcircle = findViewById(R.id.bg_circle_anim);
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.animator();
        mainBinding.setHomeprensenter(homePresenter);


    }


    @Override
    public void play() {
        Intent intent = new Intent(HomeFragment.this, tienThuongLayout.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void setting() {
        Intent intent = new Intent(HomeFragment.this, settingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }

    @Override
    public void rule() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeFragment.this);
        alertDialog.setView(R.layout.thongtin_dialog);
        final AlertDialog alertDialog1 = alertDialog.show();
        btnDong = alertDialog1.findViewById(R.id.buttonDong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }

    @Override
    public void highscore() {
        Intent intent = new Intent(HomeFragment.this, HighScoreActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void animator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgcircle, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
//        mediaPlayer.start();
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

    }
}
