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

public class FirstActivity extends AppCompatActivity {

    VideoView video_first;
    Button home_btn;
    RatingBar ratingBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //3. 화면 전환 후 이전 엑티비티에서 보낸 인텐트를 수신
        Intent intent = getIntent();
        //4. 인텐트에서 데이터를 읽음
        String text = intent.getStringExtra("MainToFirstButton");
        String text1 = intent.getStringExtra("MainToFirstButton1");
        int temp = intent.getIntExtra("MainToFirstNum",-1);
        //데이터 수신 여부 확인
        if(text!=null){
            Toast.makeText(FirstActivity.this, text1, Toast.LENGTH_LONG).show();
            //Integer.toString(temp)//숫자 출력시에 사용
        }


        home_btn = (Button)findViewById(R.id.btn_Home);
        MyListener myListener = new MyListener();
        home_btn.setOnClickListener(myListener);


        ratingBar = (RatingBar)findViewById(R.id.ratingBar_01);
        RatingListener  ratingListener = new RatingListener();
        ratingBar.setOnRatingBarChangeListener(ratingListener);


        video_first = (VideoView)findViewById(R.id.video_First);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.soapbubble;//동영상 경로와 파일 지정하여 스트링 변수에 저장
        Uri uri = Uri.parse(uriPath);//uri에 지정한 주소 입력
        video_first.setVideoURI(uri);//비디오뷰에 저장된 주소의 비디오를 연결

        final MediaController mediaController = new MediaController(this);
        video_first.setMediaController(mediaController);//비디오 뷰에 컨트롤러 생성
    }

    class MyListener implements View.OnClickListener{
        public void onClick(View view){
            //5. 데이터를 수신 후에 처리결과를 이전 액티비티로 전달하기 위한 인텐트 생성하고 처리, 데이터 추가
            Intent intent = new Intent();
            intent.putExtra("FirstToMainButton", "처음에서 메인으로 전달하는 데이터");
            intent.putExtra("FirstToMainButton1", "처음에서 메인으로 전달하는 데이터1");
            setResult(RESULT_OK, intent);//응답을 해주는 함수(잘 받았음을 의미)

            finish();//현재 보이는 화면 지우기 = 바로 직전 화면으로 이동하는 효과
        }
    }
    class RatingListener implements RatingBar.OnRatingBarChangeListener{//레이팅 바의 점수가 바뀔때 실행하는 함수

        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            Toast.makeText(FirstActivity.this,Double.toString(v),Toast.LENGTH_LONG).show();//레이팅 바 점수 표시
        }
    }
}
