package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_menu);
    }

    protected void onStart() {
        super.onStart();
        GameManager.train = false;
        GameManager.drug = 0;

        // Animation "button start"
        TextView myText = findViewById(R.id.tv_start);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        myText.startAnimation(anim);
    }

    public void start(View view){
        Intent intent = new Intent(MenuActivity.this, EnterNameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        //finish();
    }
}
