package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_pro1211.dao.HighScoreDAO;
import com.example.demo_pro1211.dilalog.GoiNguoiThanDialog;
import com.example.demo_pro1211.R;
import com.example.demo_pro1211.dilalog.ThongBaoDiaLog;
import com.example.demo_pro1211.database.Database;
import com.example.demo_pro1211.dilalog.yKienKhanGiaDialog;
import com.example.demo_pro1211.model.HighScore;

import java.util.Random;

import static com.example.demo_pro1211.activity.MainActivity.DATABASE_NAME;
import static com.example.demo_pro1211.activity.MainActivity.database;

public class PlayerActivity extends Activity implements View.OnClickListener {
    Database db;
    public MediaPlayer mediaPlayer, mediaPlayerdung, mediaPlayersai, mediaplayertimeout;
    private ImageView imgonplayer, imgoffplayer;
    private HighScoreDAO highScoreDAO;
    private Button btnSave, btnCancel;
    private EditText edname, edID;
    private ImageButton btnCall, btnKhanGia, btn5050, btnChange;

    private TextView tvTien, tvcaseA, tvcaseB, tvcaseC, tvcaseD, tvQuestion, tvLevel, tvTimer, tvScore;
    Animation animSlideToRight, animSlideFromLeft;
    private boolean isPlaying;
    boolean clickDapAn = false;
    boolean kq = false;
    boolean doiCauHoi = false, troGiup5050 = false, troGiupKhanGia = false, troGiupGoiNguoiThan = false;
    int cauSo = 1, dokho = 1, time;
    String luaChon = "", dapAn = "";
    boolean mute = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        tvQuestion = findViewById(R.id.tv_question);

        btnCall = (ImageButton) findViewById(R.id.btn_call);
        btnKhanGia = (ImageButton) findViewById(R.id.btn_khangia);
        btn5050 = (ImageButton) findViewById(R.id.btn_5050);
        btnChange = (ImageButton) findViewById(R.id.btn_change);
        imgonplayer = findViewById(R.id.imgonplayer);
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
        mediaplayertimeout = MediaPlayer.create(this, R.raw.timeout);
        imgoffplayer = findViewById(R.id.imgoffplayer);


        hienThiCauHoi();
        setEvents();
        tinhtien();
    }


    private void settingVoice() {
        imgonplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mute();
            }
        });
    }

    private void turnon() {
        imgoffplayer.setImageResource(R.drawable.ic_on);
        mediaPlayer.start();
    }

    private void mute() {
        imgonplayer.setImageResource(R.drawable.ic_off);
        mediaPlayer.stop();
        mediaPlayerdung.stop();

    }

    private void tinhtien() {
        if (tvLevel.getText().toString().equals("Câu số 1")) {
            tvTien.setText("0");
            tvTien.requestFocus();
            return;
        } else if (tvLevel.getText().toString().equals("Câu số 2")) {
            tvTien.setText("200,000");
            tvTien.requestFocus();
            return;
        } else if (tvLevel.getText().toString().equals("Câu số " + cauSo)) {
            tvTien.setText("400,000");
            tvTien.requestFocus();
            return;
        }
    }


    private void setEvents() {
        btnCall.setOnClickListener(this);
        btnKhanGia.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        imgonplayer.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btn5050.setOnClickListener(this);

        tvcaseA.setOnClickListener(this);
        tvcaseB.setOnClickListener(this);
        tvcaseC.setOnClickListener(this);
        tvcaseD.setOnClickListener(this);

    }

    private void xuLyChonDapAn(final View view) {

        if (!clickDapAn) {
            demtime.cancel();
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
                    clickDapAn = false;
                }
            }.start();
        }
    }

    CountDownTimer demtime;

    private void hienThiCauHoi() {
        mediaPlayer.start();
        try {

            database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM CauHoi where level=" + dokho + " ORDER BY RANDOM() LIMIT 1;", null);
            //cauhoi =new Question();
            if (cursor.moveToNext()) {

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
        time = 31;
        demtime = new CountDownTimer(32100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                tvTimer.setText("" + time);
            }

            @Override
            public void onFinish() {
                gameover();
            }
        }.start();
    }


    private void loaibo2phuonansai() {
        if (!troGiup5050) {
            troGiup5050 = true;
            String[] array = {"A", "B", "C", "D"};       // tim chỉ số của đáp án đúng
            int index = -1;
            int i;
            for (i = 0; i < array.length; i++) {
                if (dapAn.equals(array[i])) {
                    index = i;        //chi so la index
                }
            }
            Random r = new Random();
            //tìm 2 chỉ số của 2 đáp án SAI
            int ran1, ran2;
            do {
                ran1 = r.nextInt(3) + 1;
                ran2 = r.nextInt(3) + 1;
            }
            while (ran1 == ran2 || ran1 == index || ran2 == index);
            //hiển thị 2 đáp án SAI ra
            for (int j = 0; j < 4; j++) {
                if (ran1 == 0 || ran2 == 0) {
                    tvcaseA.setText("");

                }
                if (ran1 == 1 || ran2 == 1) {
                    tvcaseB.setText("");


                }
                if (ran1 == 2 || ran2 == 2) {
                    tvcaseC.setText("");

                }
                if (ran1 == 3 || ran2 == 3) {
                    tvcaseD.setText("");

                }

            }
            btn5050.setBackgroundResource(R.drawable.player_button_image_help_5050_x);
        }

    }

    private void ykenkhangia() {
        yKienKhanGiaDialog yKienKhanGiaDialog = new yKienKhanGiaDialog(PlayerActivity.this);

        yKienKhanGiaDialog.show();
    }

    private void goidiennguoithan() {

        //show dialog goi nguoi than
        GoiNguoiThanDialog goiNguoiThanDialog = new GoiNguoiThanDialog(PlayerActivity.this);
        ///xu ly dap an show ra
        goiNguoiThanDialog.show();
//            goiNguoiThanDialog.setCancelable(false);

    }

    private void doicauhoi() {
        if (!doiCauHoi) {
            doiCauHoi = true;
            hienThiCauHoi();
            btnChange.setBackgroundResource(R.drawable.player_button_image_help_change_question_x);
        } else {
            Toast.makeText(this, "ban da dung su tro giup nay roi", Toast.LENGTH_SHORT).show();
        }

    }


    private void gameover() {
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {


                final androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayerActivity.this);
                alertDialog.setView(R.layout.lose);
                final AlertDialog alertDialog1 = alertDialog.show();
                btnSave = alertDialog1.findViewById(R.id.btnsavelose);
                edname = alertDialog1.findViewById(R.id.edname);
                edID = alertDialog1.findViewById(R.id.edID);
                btnCancel = alertDialog1.findViewById(R.id.btncancle_lose);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highScoreDAO = new HighScoreDAO(PlayerActivity.this);

                        String id1 = edID.getText().toString();
                        String name = edname.getText().toString().trim();
                        String diem = tvTien.getText().toString();

                        int id = Integer.parseInt(id1);
                        HighScore highScore = new HighScore(id, name, diem);

                        if (highScoreDAO.insertHighscore(highScore)) {
                            Toast.makeText(PlayerActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PlayerActivity.this, HomeFragment.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(PlayerActivity.this, "that bai", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlayerActivity.this, HomeFragment.class);
                        startActivity(intent);
                    }
                });

            }
        }.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_player:
//                drawerLayout.openDrawer(GravityCompat.START);
//                break;
            case R.id.btn_call:
                goidiennguoithan();
                break;
            case R.id.btn_khangia:
                ykenkhangia();
                break;
            case R.id.btn_5050:
                loaibo2phuonansai();
                break;
            case R.id.imgoffplayer:
                turnon();
                break;
            case R.id.btn_change:
                doicauhoi();
                break;
            case R.id.imgonplayer:
                settingVoice();
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

    public void back(View view) {
        Intent intent=new Intent(PlayerActivity.this,HomeFragment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

//    public void stopThread() {
//        isPlaying = false;
//        Thread.currentThread().interrupt();
//    }
}




