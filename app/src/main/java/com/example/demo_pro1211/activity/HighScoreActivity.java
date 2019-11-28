package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_pro1211.interfaces.HighScoreView;
import com.example.demo_pro1211.presenter.HighScorePresenter;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.adapter.HighScoreAdapter;
import com.example.demo_pro1211.dao.HighScoreDAO;
import com.example.demo_pro1211.model.HighScore;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity implements HighScoreView {
    private RecyclerView rvHighScore;
    private HighScoreDAO highScoreDAO;
    private HighScorePresenter highScorePresenter;
    private ImageView imgback, imgshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        rvHighScore = findViewById(R.id.rvhighscore);
        imgback = findViewById(R.id.imgback);
        imgshare = findViewById(R.id.imgShare);
        highScoreDAO = new HighScoreDAO(HighScoreActivity.this);
        List<HighScore> highScoreList = highScoreDAO.selectHighScore();
        HighScoreAdapter highScoreAdapter = new HighScoreAdapter(HighScoreActivity.this, highScoreList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((HighScoreActivity.this));
        rvHighScore.setLayoutManager(linearLayoutManager);
        rvHighScore.setAdapter(highScoreAdapter);
        highScorePresenter = new HighScorePresenter(this);


        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScorePresenter.share();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScorePresenter.back();
            }
        });
    }


    @Override
    public void Share() {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/hieupen181020"))
                .build();
        ShareDialog.show(this, content);
    }

    @Override
    public void back() {
        Intent intent = new Intent(HighScoreActivity.this, HomeFragment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
