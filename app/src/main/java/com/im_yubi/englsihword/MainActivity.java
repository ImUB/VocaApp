package com.im_yubi.englsihword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    public static final int sub = 1001;
    public static int word = 0;
    public static String[][] EnglishWord = new String[5000][3];

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
        readtxt();
        word = randomword();
        final TextView text1 = findViewById(R.id.textView);
        text1.setText(EnglishWord[word][0]);

        TextView text2 = findViewById(R.id.textView2);
        text2.setText(EnglishWord[word][1]);
    }

    public void readtxt() {
       InputStream inputData = getResources().openRawResource(R.raw.word);
       try {
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputData, "UTF-8"));
           int i = 0;

           while(TRUE){
               String string = bufferedReader.readLine();
               if(string != null){
                   String tmp[] = string.split(":");

                   for(int j = 0; j < 2; j++){
                       EnglishWord[i][j] = tmp[j];
                   }
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
        int randomValue = random.nextInt(4197);
        return randomValue;
    }
}
