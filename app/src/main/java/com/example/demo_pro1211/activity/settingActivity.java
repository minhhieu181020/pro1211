package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.demo_pro1211.R;

public class settingActivity extends AppCompatActivity {
    private ImageView imgon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imgon = findViewById(R.id.imgon);

        imgon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgon.setImageResource(R.drawable.ic_off);

            }
        });

    }


}
