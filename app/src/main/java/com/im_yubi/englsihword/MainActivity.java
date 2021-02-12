package com.im_yubi.englsihword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int word = 0;
    public static ArrayList<Integer> WordList = new ArrayList<>();
    public static String[][] EnglishWord = new String[5000][3];
    public static int preword = 0;
    public static int state = 0;
    FileTable mfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(myClick);
        findViewById(R.id.button2).setOnClickListener(myClick);
        mfile.readtxt();
        mfile.nextword();
    }
    Button.OnClickListener myClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    startActivity(new Intent(MainActivity.this, DifficultlyActivity.class));
                    break;
                case R.id.button2:
                    if (findViewById(R.id.textView2).getVisibility() == View.VISIBLE)
                        findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                    else
                        findViewById(R.id.textView2).setVisibility(View.VISIBLE);

            }
        }
    };

    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        switch ( event.getActionMasked() ) {
            case MotionEvent.ACTION_DOWN:
                if (width/2 < x)
                    mfile.nextword();
                else
                    mfile.preword();
        }
        return super.onTouchEvent(event);
    }
}
