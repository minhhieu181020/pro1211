package com.example.demo_pro1211;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                Intent intent=new Intent(ThongBaoDiaLog.this,PlayerActivity.class);
                startActivity(intent);
            }
        });

    }
//    public ThongBaoDiaLog(Context context) {
//        super(context);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setCancelable(true);
//        setContentView(R.layout.thong_bao_dia_log);
//
//    }
//
//    public void setNotification(String notification, String textOk, String textCancle, View.OnClickListener onClickListener) {
//
//        btnOk.setText(textOk);
//        tvNotice.setText(notification);
//        btnCancle.setText(textCancle);
//        btnOk.setOnClickListener(onClickListener);
//        btnCancle.setOnClickListener(onClickListener);
//
//        if (textCancle == null) btnCancle.setVisibility(View.GONE);
//
//        if (onClickListener == null) btnOk.setOnClickListener(this);
//
//    }
//@Override
//    public void onClick(View v) {
//        dismiss();
//    }
}
