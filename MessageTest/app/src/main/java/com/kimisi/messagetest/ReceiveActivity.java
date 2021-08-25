package com.kimisi.messagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReceiveActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent = getIntent();
        String nameText = intent.getStringExtra(EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.message);



        StringBuilder ss = new StringBuilder();

        for(String s : TextList.list)
           ss.append(s).append('\n');

        textView.setText(ss);
    }


}
