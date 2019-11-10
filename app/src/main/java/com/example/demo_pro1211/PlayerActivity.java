package com.example.demo_pro1211;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.demo_pro1211.MainActivity.DATABASE_NAME;
import static com.example.demo_pro1211.MainActivity.database;

public class PlayerActivity extends Activity implements View.OnClickListener {
    Database db;
    private MediaPlayer mediaPlayer, mediaPlayerdung, mediaPlayersai;

    private DrawerLayout drawerLayout;
    private tienThuongLayout tienThuongLayout;
    private LinearLayout layoutPlay;
    private ImageButton btnCall, btnKhanGia, btn5050, btnChange;
    private ThongBaoDiaLog thongBaoDialog;
    private Button btnSanSang;

    private DrawerLayout.DrawerListener drawerListener;
    private TextView tvTien, tvcaseA, tvcaseB, tvcaseC, tvcaseD, tvQuestion, tvLevel, tvTimer, tvScore;
    Animation animSlideToRight, animSlideFromLeft;
    private boolean isPlaying;
    boolean clickDapAn = false;
    boolean kq = false;
    boolean doiCauHoi = false, troGiup5050 = false, troGiupKhanGia = false, troGiupGoiNguoiThan = false;
    int cauSo = 1, dokho = 1, time;
    String luaChon = "", dapAn = "";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        tvQuestion = findViewById(R.id.tv_question);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_player);
//tienThuongLayout=findViewById(R.id.layout_money)
        btnSanSang = (Button) findViewById(R.id.btn_sansang);
        layoutPlay = (LinearLayout) findViewById(R.id.ln_play);
        btnCall = (ImageButton) findViewById(R.id.btn_call);
        btnKhanGia = (ImageButton) findViewById(R.id.btn_khangia);
        btn5050 = (ImageButton) findViewById(R.id.btn_5050);
        btnChange = (ImageButton) findViewById(R.id.btn_change);
        tvTien = (TextView) findViewById(R.id.tv_tien);
        tvcaseA = (TextView) findViewById(R.id.tv_case_a);
        tvcaseB = (TextView) findViewById(R.id.tv_case_b);
        tvcaseC = (TextView) findViewById(R.id.tv_case_c);
        tvcaseD = (TextView) findViewById(R.id.tv_case_d);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvLevel = (TextView) findViewById(R.id.tv_level);
        tvTimer = (TextView) findViewById(R.id.tv_timer);
        mediaPlayer = MediaPlayer.create(this, R.raw.batdau);
        mediaPlayerdung = MediaPlayer.create(this, R.raw.dung);
        mediaPlayersai = MediaPlayer.create(this, R.raw.sai);

        mediaPlayer.start();
        hienThiCauHoi();
        setEvents();

    }

    private void setEvents() {
        btnChange.setOnClickListener(this);
        tvTien.setText("0");
        btn5050.setOnClickListener(this);
        tvTien.setText("0");
        tvcaseA.setOnClickListener(this);
        tvcaseB.setOnClickListener(this);
        tvcaseC.setOnClickListener(this);
        tvcaseD.setOnClickListener(this);

    }

    //    public void stop() {
//        thongBaoDialog.setCancelable(true);
//        thongBaoDialog.setNotification("Cảm ơn bạn đã đến với chúng tôi ?", "Đồng ý", "Hủy bỏ", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.btn_ok) {
//                    thongBaoDialog.dismiss();
//                    stopThread();
//                    finish();
//                }
//                thongBaoDialog.dismiss();
//            }
//        });
//        thongBaoDialog.show();
//    }
//    public void sanSang(){
//
//        thongBaoDialog.setCancelable(true);
//        thongBaoDialog.setNotification("Bạn đã sẵn sàng chưa ?", "Sẵn sàng", "Hủy bỏ", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.btn_ok) {
//                    btnSanSang.setVisibility(View.GONE);
//                    thongBaoDialog.dismiss();
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                    hienThiCauHoi();
//                }
//                thongBaoDialog.dismiss();
//            }
//        });
//        thongBaoDialog.show();
//    }
    private void hienThiCauHoi() {

        try {
            database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM CauHoi where level=" + dokho + " ORDER BY RANDOM() LIMIT 1;", null);
            //cauhoi =new Question();
            if (cursor.moveToNext()) {
            /*cauhoi.question=cursor.getString(1);
            cauhoi.caseA=cursor.getString(2);
            cauhoi.caseB=cursor.getString(3);
            cauhoi.caseC=cursor.getString(4);
            cauhoi.trueCase=cursor.getString(5);*/


                tvQuestion.setText(cursor.getString(1));
                tvcaseA.setText("A. " + cursor.getString(2));
                tvcaseB.setText("B. " + cursor.getString(3));
                tvcaseC.setText("C. " + cursor.getString(4));
                tvcaseD.setText("D. " + cursor.getString(5));
                dapAn = cursor.getString(6);
            }
            cursor.close();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex + "", Toast.LENGTH_SHORT).show();
        }
    }


//    private void xuLyChonDapAn(final View view) {
//        if (!clickDapAn) {
//            clickDapAn = true;
//            view.setBackgroundResource(R.drawable.player_answer_background_selected);
//            //tao thoi gian delay xu ly ket qua
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    new CountDownTimer(2000, 500) {
//                        boolean green = false;
//
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            if (luaChon.equals(dapAn)) {
//                                kq = true;
//                                if (!green) {
//                                    green = true;
//                                    view.setBackgroundResource(R.drawable.player_answer_background_true);
//                                    mediaPlayerdung.start();
////                                    mediaPlayersai.start();
//                                }else {
//                                    view.setBackgroundResource(R.drawable.player_answer_background_selected);
//                                    green = false;
//
//                                }
//                            }
//                            else
//                            {
//                                kq=false;   //co danh dau de biet di tiep hay dung
//                                if (!green) {
//
//                                    green = true;
//                                    switch (dapAn){
//                                        case "A":
//                                            tvcaseA.setBackgroundResource(R.drawable.player_answer_background_true);
//                                            break;
//                                        case "B":
//                                            tvcaseB.setBackgroundResource(R.drawable.player_answer_background_true);
//                                            break;
//                                        case "C":
//                                            tvcaseC.setBackgroundResource(R.drawable.player_answer_background_true);
//                                            break;
//                                        case "D":
//                                            tvcaseD.setBackgroundResource(R.drawable.player_answer_background_true);
//                                            break;
//                                    }
//
//                                } else {
//
//                                    switch (dapAn) {
//                                        case "A":
//                                            tvcaseA.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                            break;
//                                        case "B":
//                                            tvcaseB.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                            break;
//                                        case "C":
//                                            tvcaseC.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                            break;
//                                        case "D":
//                                            tvcaseD.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                            break;
//                                    }
//                                    green=false;
//                                }
//                            }
//                        }
//
//
//                        @Override
//                        public void onFinish() {
//                           if(kq) {
////                                tvTien.setText(tienThuongLayout.getMoney(cauSo));
////                                //hien so tien
////                                tienThuongLayout.setBackGroundLevel(cauSo);
//                                drawerLayout.openDrawer(GravityCompat.START);
//
//                               Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                   public void run() {
//                                        drawerLayout.closeDrawer(GravityCompat.START);
//                                       //xu ly hien diem so
////                                        // xu ly load cau tiep theo
//                                        cauSo++;
//                                        hienThiCauHoi();
////                                        if (cauSo == 6) dokho = 2;
////                                        if (cauSo == 10) dokho = 3;
//                                        hienThiCauHoi();
//                                        tvcaseA.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                        tvcaseB.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                        tvcaseC.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                        tvcaseD.setBackgroundResource(R.drawable.player_answer_background_normal);
//                                        ///////////
//                                        if(troGiup5050==true){
//                                            tvcaseA.setClickable(true);
//                                            tvcaseB.setClickable(true);
//                                            tvcaseC.setClickable(true);
//                                            tvcaseD.setClickable(true);
//                                        }
//                                       //
//                                        drawerLayout.setVisibility(View.VISIBLE);
//                                        tvLevel.setText("Câu số " + cauSo);
//                                    }
//                                }, 3000);
//                            }
//                            else{
//                               mediaPlayersai.start();
//                                //neu tra loi sai thi hien cho luu diem
//                                Toast.makeText(PlayerActivity.this,"sai cmm roi",Toast.LENGTH_SHORT).show();
//
//                                //xuLyLuuDiem();
//                            }
//
//                            //trang thai textview tro lai ban dau
//                            clickDapAn=false;
//                        }
//                    }.start();
//                }
//            }, 2000);
//        }
//    }

    private void doicauhoi() {
        if (!doiCauHoi){
            doiCauHoi = true;
            hienThiCauHoi();
            btnChange.setBackgroundResource(R.drawable.player_button_image_help_change_question_x);
        }else {
            Toast.makeText(this,"loi",Toast.LENGTH_SHORT).show();
        }

    }

    private void xuLyChonDapAn(final View view) {

        if (!clickDapAn) {
            clickDapAn = true;
            view.setBackgroundResource(R.drawable.player_answer_background_selected);
            new CountDownTimer(3000, 3000) {
                boolean green = false;

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {

                    if (luaChon.equals(dapAn)) {
                        kq = true;
                        if (!green) {
                            green = true;
                            view.setBackgroundResource(R.drawable.player_answer_background_true);
                            mediaPlayerdung.start();
                        } else {
                            view.setBackgroundResource(R.drawable.player_answer_background_selected);
                            green = false;

                        }
                        cauSo++;
                        if (cauSo == 6) dokho = 2;
                        if (cauSo == 10) dokho = 3;
                        hienThiCauHoi();
                        tvcaseA.setBackgroundResource(R.drawable.player_answer_background_normal);
                        tvcaseB.setBackgroundResource(R.drawable.player_answer_background_normal);
                        tvcaseC.setBackgroundResource(R.drawable.player_answer_background_normal);
                        tvcaseD.setBackgroundResource(R.drawable.player_answer_background_normal);

                        tvLevel.setText("Câu số " + cauSo);

                    } else {
                        kq = false;

                        if (!green) {
                            green = true;
                            switch (dapAn) {
                                case "A":
                                    tvcaseA.setBackgroundResource(R.drawable.player_answer_background_true);
                                    break;
                                case "B":
                                    tvcaseB.setBackgroundResource(R.drawable.player_answer_background_true);
                                    break;
                                case "C":
                                    tvcaseC.setBackgroundResource(R.drawable.player_answer_background_true);
                                    break;
                                case "D":
                                    tvcaseD.setBackgroundResource(R.drawable.player_answer_background_true);
                                    break;
                            }

                        } else {

                            switch (dapAn) {
                                case "A":
                                    tvcaseA.setBackgroundResource(R.drawable.player_answer_background_normal);
                                    break;
                                case "B":
                                    tvcaseB.setBackgroundResource(R.drawable.player_answer_background_normal);
                                    break;
                                case "C":
                                    tvcaseC.setBackgroundResource(R.drawable.player_answer_background_normal);
                                    break;
                                case "D":
                                    tvcaseD.setBackgroundResource(R.drawable.player_answer_background_normal);
                                    break;
                            }
                            green = false;
                        }
                        gameover();
                        mediaPlayersai.start();
                    }
                    clickDapAn=false;
                }
            }.start();
        }
    }

    private void gameover() {
        final androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerActivity.this);
        alertDialog.setView(R.layout.lose);
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_player:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.btn_change:
                doicauhoi();
                break;
            case R.id.tv_case_a:
                luaChon = "A";
                xuLyChonDapAn(tvcaseA);
                break;
            case R.id.tv_case_b:
                luaChon = "B";
                xuLyChonDapAn(tvcaseB);
                break;
            case R.id.tv_case_c:
                luaChon = "C";
                xuLyChonDapAn(tvcaseC);
                break;
            case R.id.tv_case_d:
                luaChon = "D";
                xuLyChonDapAn(tvcaseD);
                break;
            default:
                break;
        }
    }

    public void stopThread() {
        isPlaying = false;
        Thread.currentThread().interrupt();
    }
}




