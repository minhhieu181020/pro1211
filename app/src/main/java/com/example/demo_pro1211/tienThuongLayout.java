package com.example.demo_pro1211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tienThuongLayout extends AppCompatActivity {
private Button btnsansang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tien_thuong_layout);
        btnsansang=findViewById(R.id.btn_sansang);

        btnsansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(tienThuongLayout.this,PlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}
