package com.kimisi.messagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.activity01_name));
    }

    public void onClickButton(View view){


        EditText text = (EditText)findViewById(R.id.editText);
        String textS = text.getText().toString();
        TextList.addText(textS);

        Intent intent = new Intent(this, ReceiveActivity.class);
        //intent.putExtra("message", textS);
        intent.putExtra(ReceiveActivity.EXTRA_MESSAGE, textS);
        startActivity(intent);

    }

    public void onClickButton2(View view){
        EditText text = (EditText)findViewById(R.id.editText);
        String textS = text.getText().toString();
        TextList.addText(textS);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");                   // MIME type
        intent.putExtra(Intent.EXTRA_TEXT, textS);
        startActivity(intent);
    }

    public void onClickButton3(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    public void onClickButton4(View view){
        EditText text = (EditText)findViewById(R.id.editText);
        String textS = text.getText().toString();
        TextList.addText(textS);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");                   // MIME type
        intent.putExtra(Intent.EXTRA_TEXT, textS);

        Intent choose = Intent.createChooser(intent, getString(R.string.chooser));

        startActivity(choose);

    }
}
