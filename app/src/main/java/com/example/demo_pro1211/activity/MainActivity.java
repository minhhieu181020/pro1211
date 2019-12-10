package com.example.demo_pro1211.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo_pro1211.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    public static String DATABASE_NAME = "ALTPdb.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    private ImageView imageView;
    private ImageView imgload;
    private View imgcircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.truoc);
        imageView = findViewById(R.id.imageView);
        imgload = findViewById(R.id.load);
        imgcircle = findViewById(R.id.bg_circle_anim);

        animator();
        saochepCSDL();
        //---------------------------------

    }

private void animator(){
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgload, "rotation", 0f, 360f);
    objectAnimator.setDuration(1000);
    objectAnimator.setRepeatCount(1000);
    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    objectAnimator.setInterpolator(new LinearInterpolator());
    objectAnimator.start();
    //-------------------------------------------------
    ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imgcircle, "rotation", 0f, 360f);
    objectAnimator1.setDuration(10000);
    objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
    objectAnimator1.setInterpolator(new LinearInterpolator());
    objectAnimator1.start();
    new CountDownTimer(5200, 3000) {
        public void onTick(long millisUntilFinished) {
            mediaPlayer.start();
        }

        public void onFinish() {

            Intent intent = new Intent(MainActivity.this, HomeFragment.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            mediaPlayer.stop();

        }
    }.start();
}
    private void saochepCSDL() {


        File dbFile = getDatabasePath(DATABASE_NAME);

        //if(!dbFile.exists()){
        try {

            CopyDatabaseFromAsset();
//           Toast.makeText(this,"copy thành công",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show();
        }
        // }
    }

    private void CopyDatabaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDan();
            //kiem tra
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();

            }

            OutputStream myOutput = new FileOutputStream(outFileName);
            //
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, lenght);
            }
            //close stream
            myOutput.flush();
            myOutput.close();
            myInput.close();


        } catch (Exception ex) {
            Log.e("", ex.toString());
        }
    }

    private String layDuongDan() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
}
