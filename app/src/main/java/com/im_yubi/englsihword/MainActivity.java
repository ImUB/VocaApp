package com.im_yubi.englsihword;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    public static final int sub = 1001;
    public static int word = 0;
    public static ArrayList<Integer> WordList = new ArrayList<>();
    public static String[][] EnglishWord = new String[5000][3];
    public static int preword = 0;
    public static int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultlyActivity.class);
                startActivityForResult(intent, sub);
            }
        });

        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv2 = findViewById(R.id.textView2);
                if (state == 0) {
                    tv2.setVisibility(View.INVISIBLE);
                    state = 1;
                }
                else {
                    tv2.setVisibility(View.VISIBLE);
                    state = 0;
                }



            }
        });
        readtxt();
        nextword();

    }

    public boolean onTouchEvent(MotionEvent event){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float x = event.getX();
        int width = size.x;

        switch ( event.getActionMasked() ) {
            case MotionEvent.ACTION_DOWN:
                if (width/2 < x)
                    nextword();
                else
                    preword();
        }
        return super.onTouchEvent(event);
    }

    public void readtxt() {
       InputStream inputData = getResources().openRawResource(R.raw.word);
       try {
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputData, "UTF-8"));
           int i = 0;
           while(true){
               String string = bufferedReader.readLine();
               if(string != null){
                   String tmp[] = string.split(":");
                   System.arraycopy(tmp, 0, EnglishWord[i], 0, 2);
                   EnglishWord[i][2] = String.valueOf(0);
               }else{
                   break;
               }
               i++;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    } // 파일 내용 한줄씩 불러와서 EnglishWord변수에 저장

    public int randomword() {
        Random random = new Random();
        return random.nextInt(4197);
    }

    public void nextword() {
        word = randomword();
        preword = 1;
        TextView tv = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        tv.setText(EnglishWord[word][0]);
        tv2.setText(EnglishWord[word][1]);
        EnglishWord[word][2] = String.valueOf(1);
        WordList.add(0,word);
    }

    public void preword() {
        TextView tv = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        Toast myToast = Toast.makeText(this.getApplicationContext(), "이전 단어가 없습니다.", Toast.LENGTH_LONG);
        if (1 >= WordList.size()) {
            myToast.show();
        }
        else if (WordList.size() > preword){
                tv.setText(EnglishWord[WordList.get(preword)][0]);
                tv2.setText(EnglishWord[WordList.get(preword)][1]);
        }
        else
            myToast.show();
        preword++;
    }

}
