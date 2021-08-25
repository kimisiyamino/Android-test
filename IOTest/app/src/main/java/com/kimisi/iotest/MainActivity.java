package com.kimisi.iotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.editText);
        txtShow = (TextView)findViewById(R.id.textView);
    }

    public void SAVEFile(View view){
        String myTxt = edit.getText().toString();
        try{
            FileOutputStream dileOutput = openFileOutput("text.txt", MODE_PRIVATE);
            dileOutput.write(myTxt.getBytes());
            dileOutput.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void LOADFile(View view){

        try{
            FileInputStream dileInput = openFileInput("text.txt");
            InputStreamReader reader = new InputStreamReader(dileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer strBuffer = new StringBuffer();
            String lines;
            while((lines = buffer.readLine()) != null){
                strBuffer.append(lines).append("\n");
            }
            txtShow.setText(strBuffer);

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
