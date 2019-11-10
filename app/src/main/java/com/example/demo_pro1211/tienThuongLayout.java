package com.example.demo_pro1211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tienThuongLayout extends AppCompatActivity {
    private TextView tvLevel;
private Button btnsansang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tien_thuong_layout);
        btnsansang=findViewById(R.id.btn_sansang);
        tvLevel=findViewById(R.id.tv_level);

        btnsansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(tienThuongLayout.this,PlayerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
    }

//    public void setBackGroundLevel(int level) {
//        tvLevel[level - 1].setBackgroundResource(R.drawable.player_image_money_curent);
//        if (level - 2 >= 0) {
//            if (level - 1 == 5 || level - 1 == 10 || level - 1 == 15) {
//                tvLevel[level - 2].setBackgroundResource(R.drawable.player_image_money_milestone);
//                return;
//            }
//            tvLevel[level - 2].setBackground(null);
//        }
//    }
//
//    public String getMoney(int lv) {
//
//        return tvLevel[lv - 1].getText().toString();
//    }
}
