package com.example.a501_09.timerexample;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_start,btn_stop,btn_10m,btn_5m,btn_1m,btn_10s;
    TextView txt_min,txt_sec;
    int count_value = 0,which_btn = -1;
    MyAnsyncTask  myAnsyncTask=null;
    ArrayList<String> mp3_array;
    MediaPlayer player;
    ProgressBar music_progress;
    //현재 재생 하고있는 진행상황을 알려주는 함수
    int pos;
    //전체 재생 길이이
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponent();
        mp3_array = FindFileNameExtend("mp3");
        player = new MediaPlayer();
        try {
            //파일 경로를 mediaplayer 객체에 등록
            player.setDataSource(mp3_array.get(4));
            //파일을 재생할 준비
            player.prepare();
        } catch (Exception e) {
            Log.d("play mp3", "mp3 file error");
        }
        pos = player.getCurrentPosition();
        total = player.getDuration();
    }
    class MyAnsyncTask extends AsyncTask<Void,Void,Void> {
        //작업 시작 전에 계산을 위한 초기화, 컴포넌트값 초기화(프로그래스바 표시)를 처리하는 함수
        protected void onPreExecute(){
            switch (which_btn){
                case 0:
                    count_value = 600;
                    break;
                case 1:
                    count_value = 300;
                    break;
                case 2:
                    count_value = 60;
                    break;
                case 3:
                    count_value = 10;
                    break;
                case 4:
                    count_value=0;
                    break;
            }
            music_progress.setMax(count_value);
        }
        //작업을 진행하는 함수, publishProgress()함수를 호출하여 진행사항을 앱 화면에 표시함
        protected Void doInBackground(Void... arg) {//Void의 v 는 대문자로 시작,
            // Void... arg = 일에 필요한 자료가 많을 수 있기 때문에 ...으로 생략
            //siCancelled()작업이 최소 되었는지 확인하는 함수
            while(isCancelled()==false){
                count_value--;
                music_progress.incrementProgressBy(1);
                if (count_value > 0){
                    publishProgress();//제대로 작동하고 있는지 검사하기 위한 함수
                }else{
                    break;
                }
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
            }
            return null;
        }
        //작업이 진행되는동안 진행사항을 표기하기 위한 함수,publishProgress()함수를 호출하면 동작
        protected void onProgressUpdate(Void... arg){
            txt_min.setText(Integer.toString(count_value/60)+"분");
            txt_sec.setText(Integer.toString(count_value%60)+"초");
        }
        //작업을 마치고 종료되었을때 호출,odInbackground()함수의 리턴값을 매개변수로 받는다.
        protected void onPostExecute(Void result){
            player.start();
            count_value = 0;
            txt_min.setText("0분");
            txt_sec.setText("0초");
            Toast.makeText(getApplicationContext(), "타이머가 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }
        //작업을 중단하였을때 실행되는 함수
        protected void onCancelled(){
            txt_min.setText("0분");
            txt_sec.setText("0초");
        }
    }
    protected void setComponent() {
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_10m = (Button)findViewById(R.id.btn_10M);
        btn_5m = (Button)findViewById(R.id.btn_5M);
        btn_1m = (Button)findViewById(R.id.btn_1M);
        btn_10s = (Button)findViewById(R.id.btn_10S);
        txt_min = (TextView)findViewById(R.id.txt_min);
        txt_sec = (TextView)findViewById(R.id.txt_sec);
        music_progress = (ProgressBar)findViewById(R.id.music_progress);

        btn_10m.setOnClickListener(new SetTimeListener());
        btn_5m.setOnClickListener(new SetTimeListener());
        btn_1m.setOnClickListener(new SetTimeListener());
        btn_10s.setOnClickListener(new SetTimeListener());
        btn_start.setOnClickListener(new SetTimeListener());
        btn_stop.setOnClickListener(new SetTimeListener());

    }
    class SetTimeListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_10M:
                    which_btn = 0;
                    txt_min.setText("10분");
                    txt_sec.setText("0초");
                    break;
                case R.id.btn_5M:
                    which_btn = 1;
                    txt_min.setText("5분");
                    txt_sec.setText("0초");
                    break;
                case R.id.btn_1M:
                    which_btn = 2;
                    txt_min.setText("1분");
                    txt_sec.setText("0초");
                    break;
                case R.id.btn_10S:
                    which_btn = 3;
                    txt_min.setText("0분");
                    txt_sec.setText("10초");
                    break;
                case R.id.btn_start:
                    music_progress.setProgress(0);
                    if(myAnsyncTask==null) {
                        myAnsyncTask = new MyAnsyncTask();
                        //asynctask 실행
                        myAnsyncTask.execute();
                    }
                    break;
                case R.id.btn_stop:
                    which_btn = 4;
                    if(myAnsyncTask != null) {
                        myAnsyncTask.cancel(false);
                        myAnsyncTask = null;
                    }
                    music_progress.setProgress(0);
                    try {
                        if (player.isPlaying()) {
                            player.stop();
                            player.prepare();
                        }
                    } catch (Exception e) {
                        Log.d("play mp3", "mp3 file error");
                    }
                    break;
            }
        }
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
    protected void onDestroy(){
        super.onDestroy();
        if(player!=null) {
            player.release();
            player = null;
        }
    }
}
