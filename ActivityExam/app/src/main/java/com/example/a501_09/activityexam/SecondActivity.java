package com.example.a501_09.activityexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by 501-09 on 2018-02-27.
 */

public class SecondActivity extends AppCompatActivity {
    VideoView video_second;
    Button home_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar_02);
        RatingListener ratingListener = new RatingListener();
        ratingBar.setOnRatingBarChangeListener(ratingListener);

        home_btn = (Button)findViewById(R.id.btn_Home);
        MyListener homeListener = new MyListener();
        home_btn.setOnClickListener(homeListener);

        video_second = (VideoView)findViewById(R.id.video_Second);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.lamp;//동영상 경로와 파일 지정하여 스트링 변수에 저장

        Uri uri = Uri.parse(uriPath);//uri에 지정한 주소 입력
        video_second.setVideoURI(uri);//비디오뷰에 저장된 주소의 비디오를 연결

        final MediaController mediaController = new MediaController(this);
        video_second.setMediaController(mediaController);//비디오 뷰에 컨트롤러 생성
    }
    class MyListener implements View.OnClickListener{
        public void onClick(View view){
            finish();//스택
        }
    }
    class RatingListener implements RatingBar.OnRatingBarChangeListener{

        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            Toast.makeText(SecondActivity.this,Double.toString(v),Toast.LENGTH_LONG).show();
        }
    }
}
