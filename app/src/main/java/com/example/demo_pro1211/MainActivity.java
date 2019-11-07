package com.example.demo_pro1211;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgload=findViewById(R.id.load);
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imgload,"rotation",0f,720f);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(1000);
        objectAnimator.start();
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        }.start();
    }
}
