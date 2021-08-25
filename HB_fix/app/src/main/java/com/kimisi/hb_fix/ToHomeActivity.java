package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ToHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_to_home);
    }

    protected void onStart(){
        super.onStart();
        TextView tv = findViewById(R.id.text);
        tv.setText(tv.getText().toString().replaceAll("Name", GameManager.getName()));
    }

    public void way_vernii(View view) {
        Intent intent = new Intent(ToHomeActivity.this, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }

    public void way_toHome(View view) {
        Intent intent = new Intent(ToHomeActivity.this, DieTruckActivity1.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }
}
