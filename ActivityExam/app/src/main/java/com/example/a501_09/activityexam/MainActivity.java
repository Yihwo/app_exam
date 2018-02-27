package com.example.a501_09.activityexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_second,btn_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//이 코드와 화면을 연결시켜줌

        btn_first = (Button)findViewById(R.id.btn_first);
        btn_second = (Button)findViewById(R.id.btn_second);

        MyListener01 myListener01 = new MyListener01();

        btn_first.setOnClickListener(myListener01);
        btn_second.setOnClickListener(myListener01);
    }
    class MyListener01 implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent_first = new Intent(getApplicationContext(),FirstActivity.class);//다른 페이지와 연결하기 위해 필요
            //getApplicationContext() = 메인 액티비티
            Intent intent_second = new Intent(getApplicationContext(),SecondActivity.class);

            switch(view.getId()){
                case R.id.btn_first:
                    startActivity(intent_first);//intent 활성화
                    break;
                case R.id.btn_second:
                    startActivity(intent_second);//intent 활성화
                    break;
            }
        }
    }
}
