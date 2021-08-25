package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MetroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_metro);
    }

    protected void onStart(){
        super.onStart();
        TextView tv = findViewById(R.id.text);
        tv.setText(tv.getText().toString().replaceAll("Name", GameManager.getName()));

        if(GameManager.train){
            Button b = findViewById(R.id.button3);
            b.setClickable(false);
            b.setTextColor(Color.rgb(255,0,0));
            b.setText("Пройдено");
        }
    }

    public void way_gorchakova(View view){
        Intent intent = new Intent(MetroActivity.this, FamiliarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }
    // // //
    public void way_buninskaya(View view){
        Intent intent = new Intent(MetroActivity.this, BookmarkActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }

    public void way_back(View view){

            Intent intent = new Intent(MetroActivity.this, TrainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            finish();
    }
}
