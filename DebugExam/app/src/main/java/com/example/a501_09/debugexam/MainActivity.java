package com.example.a501_09.debugexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView debugtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //디버깅
        //브레이크 포인트 생성
        //debugtest = (TextView)findViewById(R.id.debug_txt);
        debugtest = null;
        int a=10;
        int b=20;
        int c=a+b;
        debugtest.setText("결과 : " + c);
        Log.d("keyword",Integer.toString(c));
    }
}
