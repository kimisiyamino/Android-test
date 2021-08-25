package com.kimisi.relativetestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_text_above;
    TextView tv_text;
    TextView tv_block;

    TextView tv_redBlock;
    TextView tv_blueBlock;
    TextView tv_yellowBlock;
    TextView tv_greenBlock;
    TextView tv_orangeBlock;
    TextView tv_pinkBlock;
    TextView tv_purpleBlock;
    TextView tv_brownBlock;

    int redColor, blueColor, yellowColor, greenColor, orangeColor, pinkColor, purpleColor, brownColor, blackColor = 0xFF000000, whiteColor = 0xFFFFFFFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_text = findViewById(R.id.tv_text);
        tv_text_above = findViewById(R.id.tv_ind);
        tv_block = findViewById(R.id.tv_block);

        tv_redBlock = findViewById(R.id.redButton);
        tv_blueBlock = findViewById(R.id.blueButton);
        tv_yellowBlock = findViewById(R.id.yellowButton);
        tv_greenBlock = findViewById(R.id.greenButton);
        tv_orangeBlock = findViewById(R.id.orangeButton);
        tv_pinkBlock = findViewById(R.id.pinkButton);
        tv_purpleBlock = findViewById(R.id.purpleButton);
        tv_brownBlock = findViewById(R.id.brownButton);

        if (tv_redBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_redBlock.getBackground();
            redColor = cd.getColor();
        }
        if (tv_blueBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_blueBlock.getBackground();
            blueColor = cd.getColor();
        }
        if (tv_yellowBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_yellowBlock.getBackground();
            yellowColor = cd.getColor();
        }
        if (tv_greenBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_greenBlock.getBackground();
            greenColor = cd.getColor();
        }
        if (tv_orangeBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_orangeBlock.getBackground();
            orangeColor = cd.getColor();
        }
        if (tv_pinkBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_pinkBlock.getBackground();
            pinkColor = cd.getColor();
        }
        if (tv_purpleBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_purpleBlock.getBackground();
            purpleColor = cd.getColor();
        }
        if (tv_brownBlock.getBackground() instanceof ColorDrawable) {
            ColorDrawable cd = (ColorDrawable) tv_brownBlock.getBackground();
            brownColor = cd.getColor();
        }


    }

    @SuppressLint("SetTextI18n")
    public void red(View view){
        tv_text.setText("layout_alignParentTop\nlayout_alignParentRight\nlayout_alignParentEnd\n" + view.getId() );
        tv_text_above.setText("Red block");
        tv_block.setBackgroundColor(redColor);
    }
    public void yellow(View view){
        tv_text.setText("layout_alignParentLeft\nlayout_alignParentStart\nlayout_alignParentTop"+ view.getId());
        tv_text_above.setText("Yellow block");
        tv_block.setBackgroundColor(yellowColor);
    }
    public void blue(View view){
        tv_text.setText("layout_alignParentBottom\nlayout_alignParentLeft\nlayout_alignParentStart"+ view.getId());
        tv_text_above.setText("Blue block");
        tv_block.setBackgroundColor(blueColor);
    }
    public void green(View view){
        tv_text.setText("layout_alignParentBottom\nlayout_alignParentRight\nlayout_alignParentEnd"+ view.getId());
        tv_text_above.setText("Green block");
        tv_block.setBackgroundColor(greenColor);
    }
    public void purple(View view){
        tv_text.setText("layout_centerHorizontal\nlayout_alignParentTop"+ view.getId());
        tv_text_above.setText("Purple block");
        tv_block.setBackgroundColor(purpleColor);
    }
    public void orange(View view){
        tv_text.setText("layout_centerVertical\nlayout_alignParentLeft\nlayout_alignParentStart"+ view.getId());
        tv_text_above.setText("Orange block");
        tv_block.setBackgroundColor(orangeColor);
    }

    public void pink(View view){
        tv_text.setText("layout_toRightOf\nlayout_toEndOf\nlayout_below"+ view.getId());
        tv_text_above.setText("Pink block");
        tv_block.setBackgroundColor(pinkColor);
    }

    public void brown(View view){
        tv_text.setText("layout_above\nlayout_toLeftOf\nlayout_toStartOf"+ view.getId());
        tv_text_above.setText("Brown block");
        tv_block.setBackgroundColor(brownColor);
    }


    public void textCenter(View view){
        tv_text.setText("layout_centerInParent"+ view.getId());
        tv_text_above.setText("This text");
        tv_block.setBackgroundColor(whiteColor);
    }
}
