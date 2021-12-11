package com.im_yubi.englsihword;


import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
public class

MainActivity extends AppCompatActivity {
    private boolean test_btn = true;
    String e_word;
    String k_word;
    private DbOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(myClick);
        findViewById(R.id.button).setOnClickListener(myClick);

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        readtxt();
    }

    Button.OnClickListener myClick = v -> {

        if( v.getId() == R.id.button2){
            if( test_btn) {
                findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                test_btn = false;
            } else {
                findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                test_btn = true;
            }
        }
        if( v.getId() == R.id.button) {
            Intent intent = new Intent(this, DifficultlyActivity.class);
            startActivity(intent);
        }
    }; // 버튼 클릭 이벤트 구현

    public void readtxt() {
        Intent intent = getIntent();
        String data = intent.getExtras().getString("word");
        Resources res = getResources();
        int Id = res.getIdentifier(data, "raw", getPackageName());

        InputStream inputData = getResources().openRawResource(Id);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputData, StandardCharsets.UTF_8));
            while (true) {
                String string = bufferedReader.readLine();
                if (string != null) {
                    String[] tmp;
                    tmp = string.split(":");
                    if ( tmp.length != 1) {
                        switch (data) {
                            case "officer":
                                e_word = tmp[0];
                                k_word = tmp[1];
                                mDbOpenHelper.officerinsertColumn(e_word, k_word);
                                break;
                            case "sat":
                                e_word = tmp[0];
                                k_word = tmp[1];
                                mDbOpenHelper.satinsertColumn(e_word, k_word);
                                break;
                            case "toeic":
                                e_word = tmp[0];
                                k_word = tmp[1];
                                mDbOpenHelper.toeicinsertColumn(e_word, k_word);
                                break;
                        }
                    } else {
                        Log.d("read", "에러.");
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // 파일 내용 한줄씩 불러와서 EnglishWord변수에 저장
}