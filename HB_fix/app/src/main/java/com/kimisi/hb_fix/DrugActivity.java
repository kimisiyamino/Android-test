package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DrugActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_drug);
    }

    protected void onStart(){
        super.onStart();
        TextView tv = findViewById(R.id.text);
        tv.setText(tv.getText().toString().replaceAll("Name", GameManager.getName()));
    }
    //
    public void way_entrance(View view){
        Intent intent = null;

        if(GameManager.fastFinish && GameManager.theSecondFinish && GameManager.SecretFinish){
            intent = new Intent(DrugActivity.this, FullCompleteActivity.class);
        }

        if(GameManager.drug == 0) {
            intent = new Intent(DrugActivity.this, DieCopsEntranceActivity.class);// lose
        }else if(GameManager.drug == 1 && !GameManager.theSecondFinish) {
            GameManager.theSecondFinish = true;
            intent = new Intent(DrugActivity.this, Win2StreetActivity.class);
        }else if(GameManager.drug == 1 && GameManager.theSecondFinish) {
            intent = new Intent(DrugActivity.this, CompleteActivity.class);
        }else if(GameManager.drug == 2 && !GameManager.SecretFinish) {
            GameManager.SecretFinish = true;
            intent = new Intent(DrugActivity.this, Win2StreetActivity.class);//SuperWinActivity //Какая то ебанутая хуйня
        }else {
            intent = new Intent(DrugActivity.this, FullCompleteActivity.class);
        }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            finish();
    }
    //
    public void way_street(View view){
        Intent intent = null;

        if(GameManager.fastFinish && GameManager.theSecondFinish && GameManager.SecretFinish){
            intent = new Intent(DrugActivity.this, FullCompleteActivity.class);
        }

        if(GameManager.drug == 0 && !GameManager.fastFinish) {
            GameManager.fastFinish = true;
            intent = new Intent(DrugActivity.this, Win1EntranceActivity.class);//WinStreetActivity
        }else if(GameManager.drug == 0 && GameManager.fastFinish) {
            intent = new Intent(DrugActivity.this, CompleteActivity.class);
        }else if(GameManager.drug == 1) {
            intent = new Intent(DrugActivity.this, DieCopsStreetActivity.class);
        }else if(GameManager.drug == 2 && !GameManager.SecretFinish) {
            GameManager.SecretFinish = true;
            intent = new Intent(DrugActivity.this, Win1EntranceActivity.class); //SuperWinActivity//Какая то ебанутая хуйня
        }else {
            intent = new Intent(DrugActivity.this, FullCompleteActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
    }
}