package com.kimisi.hb_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        GameManager.setFull(this);

        setContentView(R.layout.activity_enter_name);
    }
    protected void onStart(){
        super.onStart();
        if(GameManager.getName() != null){
            EditText et = findViewById(R.id.editText);
            et.setText(GameManager.getName());
        }
    }
    public void go(View view){
        EditText et = findViewById(R.id.editText);
        if(!(et.getText() == null || et.getText().toString().equals(""))){
            GameManager.setName(et.getText().toString());
            Intent intent = new Intent(EnterNameActivity.this, MetroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            finish();
        }
    }
}
