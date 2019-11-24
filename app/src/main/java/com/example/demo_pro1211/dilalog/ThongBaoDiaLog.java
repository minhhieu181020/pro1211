package com.example.demo_pro1211.dilalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.activity.PlayerActivity;

public class ThongBaoDiaLog extends AppCompatActivity  {
    public Button btnCancle;
    public Button btnOk;
    private TextView tvNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_bao_dia_log);
        btnCancle =  findViewById(R.id.btn_cancle);
        btnOk =  findViewById(R.id.btn_ok);
        tvNotice =  findViewById(R.id.tv_notice);
        getWindow().setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThongBaoDiaLog.this, PlayerActivity.class);
                startActivity(intent);
            }
        });

    }

}
