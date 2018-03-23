package com.example.a501_09.mp3playertest;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mp3_array;
    int mp3_index=0;
    Button btn_play, btn_stop, btn_pre, btn_nxt;
    TextView txt_title;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp3_array = FindFileNameExtend("mp3");

        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_pre = (Button) findViewById(R.id.btn_pre);
        btn_nxt = (Button) findViewById(R.id.btn_nxt);
        txt_title = (TextView) findViewById(R.id.txt_title);

        //mp3경로 입력
        //음악 재생을 위한 mediaplayer객체 생성
        player = new MediaPlayer();
        try {
            //파일 경로를 mediaplayer 객체에 등록
            player.setDataSource(mp3_array.get(mp3_index));
            //파일을 재생할 준비
            player.prepare();
        } catch (Exception e) {
            Log.d("play mp3", "mp3 file error");
        }
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {//음악 파일이 재생중인지 확인하는 함수
                    player.pause();
                    btn_play.setText("PLAY");
                } else {
                    player.start();

//                    //반복 재생
//                    player.setLooping(true);
//
//                    //현재 재생 하고있는 진행상황을 알려주는 함수
//                    int pos = player.getCurrentPosition();
//                    //전체 재생 길이이
//                    int total = player.getDuration();
                    btn_play.setText("PAUSE");
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.stop();
                    btn_play.setText("PLAY");
                }
                try {
                    player.prepare();
                } catch (Exception e) {
                }
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp3_index < mp3_array.size()-1) {
                    mp3_index++;
                }else{
                    mp3_index=0;
                }
                //mp3_index = (mp3_index<0) ? mp3_index++ : (0);//3항 연산자로 표현
                txt_title.setText(mp3_array.get(mp3_index));

                try {
                    if (player.isPlaying()) {
                        player.stop();
                        player.prepare();
                    }
                    player.reset();
                    //파일 경로를 mediaplayer 객체에 등록
                    player.setDataSource(mp3_array.get(mp3_index));
                    //파일을 재생할 준비
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    Log.d("play mp3", "mp3 file error");
                }
            }
        });
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp3_index > 0) {
                    mp3_index--;
                }else{
                    mp3_index = mp3_array.size()-1;
                }
                //mp3_index = (mp3_index<0) ? mp3_index-- : (mp3_array.size()-1);//3항 연산자로 표현
                try {
                    if (player.isPlaying()) {
                        player.stop();
                        player.prepare();
                    }
                    player.reset();
                    //파일 경로를 mediaplayer 객체에 등록
                    player.setDataSource(mp3_array.get(mp3_index));
                    //파일을 재생할 준비
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    Log.d("play mp3", "mp3 file error");
                }
            }
        });
    }

    @Nullable
    private ArrayList<String> FindFileNameExtend(String ext) {
        ArrayList<String> temp = new ArrayList<String>();
        final String file_ext = ext;
        //sd card가 제대로 있는지 확인
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //sd card의 경로
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            //원하는 파일만 추출
            FilenameFilter file_filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    //ext로 받아온 단어로 끝나는 파일 찾기
                    return s.endsWith(file_ext);
                }
            };
            File sdRoot = new File(sdPath);
            //sdRoot 안에서 필터가 적용된 파일만 입력됨
            String[] file_list = sdRoot.list(file_filter);
            String temp_file = "";

            for (int i = 0; i < file_list.length; i++) {
                temp_file += file_list[i] + "\n";
                //파일의 경로 와 파일명
                temp.add(sdPath + "/" + file_list[i]);
            }
            return temp;
        } else {
            Toast.makeText(this, "sd card의 인식이 불가합니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
