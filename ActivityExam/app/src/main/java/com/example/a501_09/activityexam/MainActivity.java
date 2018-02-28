package com.example.a501_09.activityexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_second,btn_first,btn_bp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//이 코드와 화면을 연결시켜줌

        btn_first = (Button)findViewById(R.id.btn_first);
        btn_second = (Button)findViewById(R.id.btn_second);
        btn_bp = (Button)findViewById(R.id.bp_btn);

        MyListener01 myListener01 = new MyListener01();

        btn_first.setOnClickListener(myListener01);
        btn_second.setOnClickListener(myListener01);
        btn_bp.setOnClickListener(myListener01);
    }
    class MyListener01 implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent;
            //데이터를 송신할 엑티비티에서 전달할 인텐트를 만듬

            switch(view.getId()){
                case R.id.btn_first:
                    //1. 인텐트 만들기 //getApplicationContext() = 현재 자신의 액티비티
                    intent= new Intent(MainActivity.this,FirstActivity.class);//다른 페이지와 연결하기 위해 필요
                    //startActivity(intent);//intent 활성화
                    //2. 전달할 인텐트에 데이터 추가, 화면 전환
                    intent.putExtra("MainToFirstButton","메인에서 첫 페이지로");
                    intent.putExtra("MainToFirstButton1","메인에서 첫 페이지로1");
                    intent.putExtra("MainToFirstNum","메인에서 첫 페이지로 숫자");

                    startActivityForResult(intent,1);//requestCode는 순서를 확인하기 위한 장치
                    break;
                case R.id.btn_second:
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(intent);//intent 활성화
                    break;
                case R.id.bp_btn:
                    intent = new Intent(getApplicationContext(),VideoActivity.class);
                    startActivity(intent);//intent 활성화
                    break;
            }
        }
    }
    //6. 액티비티 B에서 받은 인텐트 데이터를 처리하는 함수
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Toast.makeText(MainActivity.this,data.getStringExtra("FirstToMainButton1"),
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
