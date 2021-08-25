package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fullscreen mode
        GameManager.setFull(this);
        setContentView(R.layout.activity_forest);
    }
    protected void onStart(){
        super.onStart();
        TextView tv = findViewById(R.id.text);
        tv.setText(tv.getText().toString().replaceAll("Name", GameManager.getName()));
    }
    public void way_run(View view){
        Intent intent = new Intent(ForestActivity.this, DieFActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }

    public void way_stay(View view){
        GameManager.drug = 2;
        Intent intent = new Intent(ForestActivity.this, StayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }


}
