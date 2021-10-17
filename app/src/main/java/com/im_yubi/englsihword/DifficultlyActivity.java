package com.im_yubi.englsihword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DifficultlyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficultly);

        findViewById(R.id.sat).setOnClickListener(myclick);
        findViewById(R.id.toeic).setOnClickListener(myclick);
        findViewById(R.id.officer).setOnClickListener(myclick);
        findViewById(R.id.back_btn).setOnClickListener(myclick);
    }

    Button.OnClickListener myclick = v -> {
        Intent intent = new Intent(this, MainActivity.class);
        if( v.getId() == R.id.sat) {
            intent.putExtra("word","sat");
            startActivity(intent);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "수능 영단어를 선택하셨습니다.", Toast.LENGTH_LONG);
            myToast.show();
        }
        else if( v.getId() == R.id.toeic) {
            intent.putExtra("word","toeic");
            startActivity(intent);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "토익 영단어를 선택하셨습니다.", Toast.LENGTH_LONG);
            myToast.show();
        }
        else if( v.getId() == R.id.officer) {
            intent.putExtra("word","officer");
            startActivity(intent);
            Toast myToast = Toast.makeText(this.getApplicationContext(), "공무원 영단어를 선택하셨습니다.", Toast.LENGTH_LONG);
            myToast.show();
        }
        else if(v.getId() == R.id.back_btn) {
            finish();
        }
    };
}
