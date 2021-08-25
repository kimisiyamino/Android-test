package com.kimisi.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int stateStopWatch = 0; // stopped - 0; started - 1; pause - 2;
    public Handler handler;
    public TextView timerText;

    public long firstTime = 0L;
    public long pastTime = 0L;
    public long saveTime = 0L;
    public long updateTime = 0L;

    public long exitTime = 0L;

    ArrayList<Integer> imageSnusList;
    ImageView imageSnus;
    int numOfImage;

    ButtonAnimation buttonAnimation;
    ImagesSnus imagesSnus;

    //public static final String FILE_preferences = "preferences2";
    //public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.ActivityMain_name));

        // Поток
        handler = new Handler();

        // Дисплей таймера
        timerText = (TextView)findViewById(R.id.timer);
        timerText.setMaxLines(1);

        // Картинка Снюс
        imagesSnus = new ImagesSnus();
        imageSnusList = imagesSnus.createImageList();
        imageSnus = (ImageView)findViewById(R.id.imageView);
        imageSnus.setImageResource(imageSnusList.get((int)(Math.random() * imageSnusList.size())));
        // Менеджер Данных

        /*
        if (preferences.contains("exitTime")) {
            exitTime = preferences.getLong("exitTime", 0);
        }
        if (preferences.contains("saveTime")) {
            saveTime = preferences.getLong("saveTime", 0);
        }
        if (preferences.contains("stateStopWatch")) {
            stateStopWatch = preferences.getInt("stateStopWatch", 0);
            dataManageOnResume();
        }*/




        // Анимация кнопок

    }

    protected  void onStart() {
        super.onStart();
        SharedPreferences preferences = this.getSharedPreferences("com.kimisi.stopwatchXXX", Context.MODE_PRIVATE);
        if(preferences != null) {
            exitTime = preferences.getLong("exitTime", 0);
            saveTime = preferences.getLong("saveTime", 0);
            stateStopWatch = preferences.getInt("stateStopWatch", 0);
            dataManageOnResume();
        }
        buttonAnimation = new ButtonAnimation();
        buttonAnimation.onCreate(this);
    }


    public void dataManageOnResume(){
        if(stateStopWatch == 1) {
            firstTime = System.currentTimeMillis();
            saveTime = saveTime + (firstTime - exitTime);
            //saveTime = 9_223_372_036_854_700_000L;

            // 9_223_372_036_854_775_807L;
            // 4_223_372_036_854_775_807L
           //          1_590_950_264_167
            handler.post(imageUpdateThread);
            handler.post(timeUpdateThread);


        } else if (stateStopWatch == 2){
            handler.post(timeUpdateThread);
            imageSnus.setImageResource(imageSnusList.get((int)(Math.random() * imageSnusList.size())));


        } else{
            imageSnus.setImageResource(imageSnusList.get((int)(Math.random() * imageSnusList.size())));


        }
    }

    protected  void onStop(){
        super.onStop();
        //prefs.edit().putString("tag", editText.getText().toString()).apply();

        exitTime = System.currentTimeMillis();
        saveTime += pastTime;

        SharedPreferences preferences = this.getSharedPreferences("com.kimisi.stopwatchXXX", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("exitTime", exitTime);
        editor.putLong("saveTime", saveTime);
        editor.putInt("stateStopWatch", stateStopWatch);
        editor.apply();

        handler.removeCallbacks(timeUpdateThread);
        handler.removeCallbacks(imageUpdateThread);

    }
    protected void onDestroy(){
        super.onDestroy();

    }
    public void onClickStart(View view){
        if(stateStopWatch == 0 || stateStopWatch == 2) {
            buttonAnimation.onClickStartAnimation();
            stateStopWatch = 1;
            firstTime = System.currentTimeMillis();

            handler.post(timeUpdateThread);
            handler.post(imageUpdateThread);
        }
    }
    public void onClickPause(View view){
        if(stateStopWatch == 1) {
            buttonAnimation.onClickPauseAnimation();
            stateStopWatch = 2;
            saveTime += pastTime;
            pastTime = 0L;

            handler.removeCallbacks(timeUpdateThread);
            handler.removeCallbacks(imageUpdateThread);
        }
    }
    @SuppressLint("SetTextI18n")
    public void onClickReset(View view){
        if(stateStopWatch == 1 || stateStopWatch == 2) {
            buttonAnimation.onClickResetAnimation();
            stateStopWatch = 0;
            handler.removeCallbacks(timeUpdateThread);
            timerText.setText("00:00:00");

            firstTime = 0L;
            pastTime = 0L;
            saveTime = 0L;
            updateTime = 0L;

            handler.removeCallbacks(imageUpdateThread);
        }
    }

    Runnable timeUpdateThread = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            if(stateStopWatch == 1) {
                pastTime = System.currentTimeMillis() - firstTime;
                updateTime = saveTime + pastTime;
            }else updateTime = saveTime;

            int sec = (int)(updateTime / 1000) % 60;
            int min = (int) updateTime / (60 * 1000) % 60;
            int hours = (int) updateTime / (60 * 60 * 1000);
            int millis = (int)(updateTime % 1000) / 10;

            String formatTextTimer;

            if(hours != 0)
                formatTextTimer = String.format("%02d:%02d:%02d:%02d", hours, min, sec, millis);
            else
                formatTextTimer = String.format("%02d:%02d:%02d", min, sec, millis);

            timerText.setText(formatTextTimer);

            handler.postDelayed(this, 0);
        }
    };

    Runnable imageUpdateThread = new Runnable() {
        @Override
        public void run() {
            if (imageSnusList.isEmpty())
                imageSnusList = imagesSnus.createImageList();

            int i = imageSnusList.size();

            numOfImage = (int) (Math.random() * i);
            imageSnus.setImageResource(imageSnusList.get(numOfImage));
            imageSnusList.remove(numOfImage);

            handler.postDelayed(this, 3000);
        }
    };



    class ButtonAnimation {

        private FloatingActionButton fabButtonStart, fabButtonPause, fabButtonStop;
        private Animation animShowFab, animHideFab, empty;

        public void onCreate(MainActivity Main){
            animShowFab = AnimationUtils.loadAnimation(Main, R.anim.fab_show);
            animHideFab = AnimationUtils.loadAnimation(Main, R.anim.fab_hide);
            empty = AnimationUtils.loadAnimation(Main, R.anim.empty);

            fabButtonStart = (FloatingActionButton)Main.findViewById(R.id.ButtonStart);
            fabButtonPause = (FloatingActionButton)Main.findViewById(R.id.ButtonPause);
            fabButtonStop = (FloatingActionButton)Main.findViewById(R.id.ButtonReset);

            if(stateStopWatch == 1){
                fabButtonStart.hide();
            }
            else if(stateStopWatch == 2){
                fabButtonPause.hide();
            }else{
                fabButtonPause.hide();
                fabButtonStop.hide();
            }
        }
        public void onClickStartAnimation(){
            if(stateStopWatch == 2){
                fabButtonStart.startAnimation(animHideFab);
                fabButtonPause.show();
                fabButtonPause.startAnimation(animShowFab);
            }
            else if(stateStopWatch == 0){
                fabButtonStart.startAnimation(animHideFab);
                fabButtonPause.show();
                fabButtonStop.show();
                fabButtonPause.startAnimation(animShowFab);
                fabButtonStop.startAnimation(animShowFab);
            }
        }
        public void onClickPauseAnimation(){
                fabButtonStart.show();
                fabButtonStart.startAnimation(animShowFab);
                fabButtonPause.startAnimation(animHideFab);
                fabButtonPause.hide();
                fabButtonStop.startAnimation(empty);
        }
        public void onClickResetAnimation(){
            if(stateStopWatch == 1){
                fabButtonStart.show();
                fabButtonStart.startAnimation(animShowFab);
                fabButtonPause.startAnimation(animHideFab);
                fabButtonPause.hide();
                fabButtonStop.startAnimation(animHideFab);
                fabButtonStop.hide();
            }
            else if(stateStopWatch == 2){
                fabButtonStop.startAnimation(animHideFab);
            }
        }
    }
}