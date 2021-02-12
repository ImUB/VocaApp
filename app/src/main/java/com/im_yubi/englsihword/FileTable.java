package com.im_yubi.englsihword;

import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class FileTable extends MainActivity{
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
