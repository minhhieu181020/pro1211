package com.example.demo_pro1211;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgload;
    private View imgcircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgload=findViewById(R.id.load);
        imgcircle=findViewById(R.id.bg_circle_anim);
        //---------------------------------
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imgload,"rotation",0f,360f);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(1000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        //-------------------------------------------------
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(imgcircle,"rotation",0f,360f);
        objectAnimator1.setDuration(10000);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setInterpolator(new LinearInterpolator());
        objectAnimator1.start();
        new CountDownTimer(3000, 3000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, HomeFragment.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        }.start();
    }
}
