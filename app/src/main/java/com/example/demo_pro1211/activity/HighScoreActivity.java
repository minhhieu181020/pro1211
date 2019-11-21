package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.demo_pro1211.R;
import com.example.demo_pro1211.adapter.HighScoreAdapter;
import com.example.demo_pro1211.dao.HighScoreDAO;
import com.example.demo_pro1211.model.HighScore;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    private RecyclerView rvHighScore;
    private HighScoreDAO highScoreDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        rvHighScore=findViewById(R.id.rvhighscore);

        highScoreDAO = new HighScoreDAO(HighScoreActivity.this);
        List<HighScore> highScoreList = highScoreDAO.selectHighScore();
        HighScoreAdapter highScoreAdapter = new HighScoreAdapter(HighScoreActivity.this, highScoreList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((HighScoreActivity.this));
        rvHighScore.setLayoutManager(linearLayoutManager);
        rvHighScore.setAdapter(highScoreAdapter);
        try {

            PackageInfo info = getPackageManager().getPackageInfo(

                    "com.example.demo_pro1211",

                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());

                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }

        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
       CallbackManager callbackManager = CallbackManager.Factory.create();

    }

    public void Share(View view) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.facebook.com/hieupen181020"))
                .build();
        ShareDialog.show(this,content);
    }

    public void back(View view) {
        Intent intent=new Intent(HighScoreActivity.this,HomeFragment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
