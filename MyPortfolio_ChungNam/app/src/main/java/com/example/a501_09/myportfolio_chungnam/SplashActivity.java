package com.example.a501_09.myportfolio_chungnam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by 501-09 on 2018-03-30.
 */
//첫 로딩동안 나오는 화면
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UiHiddenNaviBar uiHiddenNaviBar = new UiHiddenNaviBar(SplashActivity.this);
//        //앱을 동작하기 위한 준비를 위한 시간
//        try{
//            Thread.sleep(5000);
//            Toast.makeText(this, "5초 ", Toast.LENGTH_SHORT).show();
//        }catch(Exception e){;}

        Intent intent = new Intent(SplashActivity.this,ScheduleTripActivity.class);
        startActivity(intent);

        finish();
    }
}
