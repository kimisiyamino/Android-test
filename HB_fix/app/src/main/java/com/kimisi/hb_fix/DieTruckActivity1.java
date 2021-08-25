package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DieTruckActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_die_truck1);
    }

    protected void onStart(){
        super.onStart();
        TextView tv = findViewById(R.id.text);
        tv.setText(tv.getText().toString().replaceAll("Name", GameManager.getName()));
    }

    public void next(View view){
        Intent intent = new Intent(DieTruckActivity1.this, DieTruckActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }
}
