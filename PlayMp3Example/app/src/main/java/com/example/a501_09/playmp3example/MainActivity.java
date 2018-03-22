package com.example.a501_09.playmp3example;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_play,btn_stop;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button)findViewById(R.id.btn_stop);

        //mp3경로 입력
        String mp3_path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Punch.mp3";
        //음악 재생을 위한 mediaplayer객체 생성
        player = new MediaPlayer();
        try{
            //파일 경로를 mediaplayer 객체에 등록
            player.setDataSource(mp3_path);
            //파일을 재생할 준비
            player.prepare();
        }catch(Exception e){
            Log.d("play mp3", "mp3 file error");
        }
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()){//음악 파일이 재생중인지 확인하는 함수
                    player.pause();
                    btn_play.setText("PLAY");
                }else{
                    player.start();

                    //반복 재생
                    player.setLooping(true);

                    //현재 재생 하고있는 진행상황을 알려주는 함수
                    int pos = player.getCurrentPosition();
                    //전체 재생 길이이
                    int total = player.getDuration();
                    btn_play.setText("PAUSE");
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.isPlaying()) {
                    player.stop();
                    btn_play.setText("PLAY");
                }
                try {
                    player.prepare();
                }catch(Exception e){}
            }
        });
    }
    public  void onDestroy(){
        super.onDestroy();
        if(player !=null) {
            player.release();//mp3 미디어 플레이어 완전 종료
        }
    }

}
