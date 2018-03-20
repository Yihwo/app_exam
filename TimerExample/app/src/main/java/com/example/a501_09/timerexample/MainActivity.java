package com.example.a501_09.timerexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_start,btn_stop,btn_10m,btn_5m,btn_1m,btn_10s;
    TextView txt_min,txt_sec;
    int count_value = 0,which_btn = -1;
    MyAnsyncTask  myAnsyncTask=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponent();
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
            }
        }
        //작업을 진행하는 함수, publishProgress()함수를 호출하여 진행사항을 앱 화면에 표시함
        protected Void doInBackground(Void... arg) {//Void의 v 는 대문자로 시작,
            // Void... arg = 일에 필요한 자료가 많을 수 있기 때문에 ...으로 생략
            //siCancelled()작업이 최소 되었는지 확인하는 함수
            while(isCancelled()==false){
                count_value--;
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
                    if(myAnsyncTask==null) {
                        myAnsyncTask = new MyAnsyncTask();
                        //asynctask 실행
                        myAnsyncTask.execute();
                    }
                    break;
                case R.id.btn_stop:
                    if(myAnsyncTask != null) {
                        myAnsyncTask.cancel(false);
                        myAnsyncTask = null;
                    }
                    break;
            }
        }
    }
}
