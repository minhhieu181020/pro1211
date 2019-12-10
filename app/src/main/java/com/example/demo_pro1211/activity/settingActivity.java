package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.interfaces.SettingView;
import com.example.demo_pro1211.presenter.SettingPresenter;

public class settingActivity extends AppCompatActivity implements SettingView {
    private ImageView imgon,imgon1;

    private SettingPresenter settingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imgon = findViewById(R.id.imgon);
        imgon1=findViewById(R.id.imgon1);
        settingPresenter=new SettingPresenter(this);
        imgon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingPresenter.mute();
            }
        });
        imgon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingPresenter.mute();
            }
        });


    }


    @Override
    public void mute() {
        imgon.setImageResource(R.drawable.ic_off);
    }
}
