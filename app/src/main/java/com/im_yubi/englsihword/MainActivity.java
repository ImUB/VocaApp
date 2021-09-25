package com.im_yubi.englsihword;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class

MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static int word = 0;
    public static int randomword = 0;
    public static ArrayList<Integer> WordList = new ArrayList<>();
    public static String[][] EnglishWord = new String[5000][3];
    public static int preword = 0;
    private boolean test_btn = true;
    View v_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v_progress = findViewById(R.id.progress);
        v_progress.setVisibility(View.GONE);
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        findViewById(R.id.b_class).setOnClickListener(myClick);
        findViewById(R.id.button2).setOnClickListener(myClick);
        findViewById(R.id.button4).setOnClickListener(myClick);
        findViewById(R.id.button).setOnClickListener(myClick);
        findViewById(R.id.content).setOnTouchListener( this );
        findViewById(R.id.b_progress).setOnClickListener(myClick);

        readtxt();
        randomword();
        nextword();
    }


    Button.OnClickListener myClick = v -> {
        DrawerLayout drawer = findViewById(R.id.drawer);

        if( v.getId() == R.id.b_class)
            startActivity(new Intent(MainActivity.this, DifficultlyActivity.class));
        if( v.getId() == R.id.button2){
            if( test_btn) {
                findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                test_btn = false;
            } else {
                findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                test_btn = true;
            }
        }
        if (v.getId() == R.id.button4) {
            if(!drawer.isDrawerOpen(Gravity.START)) {
                drawer.openDrawer(Gravity.START);
            } else {
                drawer.closeDrawer(Gravity.START);

            }
        }
        if ( v.getId() == R.id.button) {
            EnglishWord[randomword][2] = String.valueOf(101);
            knownword();
            Toast myToast = Toast.makeText(this.getApplicationContext(), "암기 완료", Toast.LENGTH_SHORT);
            myToast.show();
        }
        if ( v.getId() == R.id.b_progress) {
            if (v_progress.getVisibility() == View.GONE) {
                v_progress.setVisibility(View.VISIBLE);
                int wordsize = wordsize();

                String wordsize1 = Integer.toString(wordsize);

                TextView t1 = findViewById(R.id.entire);
                t1.setText(wordsize1);
            } else
                v_progress.setVisibility(View.GONE);
        }
    }; // 버튼 클릭 이벤트 구현

    @Override
    public boolean onTouch(View view, MotionEvent ev) {
        boolean ret = false;

        if(view.getId() == R.id.content){
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            float width = metrics.widthPixels;
            float x = ev.getX();

            if ( ev.getActionMasked() == MotionEvent.ACTION_DOWN ) {
                if ( width / 2 < x) {
                    randomword();
                    nextword();
                }
                else
                    preword();
            }
            else if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
                view.performClick();
            }
            ret = true;
        }
        return ret;
    }

    public void readtxt() {
        InputStream inputData = getResources().openRawResource(R.raw.word);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputData, StandardCharsets.UTF_8));
            int i = 0;
            while (true) {
                String string = bufferedReader.readLine();
                if (string != null) {
                    String[] tmp;
                    tmp = string.split(":");
                    if ( tmp.length != 1) {
                        System.arraycopy(tmp, 0, EnglishWord[i], 0, 2);
                        EnglishWord[i][2] = String.valueOf(101);
                    } else {
                        Log.v("read", "에러 :" + i + "번째입니다.");
                    }
                } else {
                    break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // 파일 내용 한줄씩 불러와서 EnglishWord변수에 저장


    public void nextword() {
        word = randomword;
        preword = 1;
        TextView tv = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        tv.setText(EnglishWord[word][0]);
        tv2.setText(EnglishWord[word][1]);
        WordList.add(0, word);
    }

    public void preword() {
        TextView tv = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        Toast myToast = Toast.makeText(this.getApplicationContext(), "이전 단어가 없습니다.", Toast.LENGTH_LONG);
        if (1 >= WordList.size()) {
            myToast.show();
        } else if (WordList.size() > preword) {
            tv.setText(EnglishWord[WordList.get(preword)][0]);
            tv2.setText(EnglishWord[WordList.get(preword)][1]);
        } else
            myToast.show();
        preword++;
    }

    public int wordsize() {
        int wordsize = 0;
        for (int i = 0; i < EnglishWord.length; i++){
            if (EnglishWord[i][0] == null) {
                wordsize = i;
                break;
            }
        }
        return wordsize;
    }

    public void knownword() {
        int known = 0;
        int wordsize = wordsize();
        for (int i=0; i < wordsize; i++) {
            if (EnglishWord[i][2].equals("101")) {
                known++; // "101"을 파일에 직접 입력해야함..... 안그러면 소용없음
            }
        }
    }

    public void randomword() {
        int n_random = wordsize();
        Random random = new Random();

        randomword = random.nextInt(n_random);
    }


}
