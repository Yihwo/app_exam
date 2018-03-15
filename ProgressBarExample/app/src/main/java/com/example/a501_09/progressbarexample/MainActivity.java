package com.example.a501_09.progressbarexample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Switch aSwitch;
    ProgressBar progressBar_Horizen;
    ProgressBar progressBar_three;
    Button leftToRight, rightToLeft, three_btn, toast_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar_Horizen = (ProgressBar) findViewById(R.id.progressBarHorizen);
        progressBar_three = (ProgressBar) findViewById(R.id.progress_three);
        aSwitch = (Switch) findViewById(R.id.switch1);
        leftToRight = (Button) findViewById(R.id.ltor_btn);
        rightToLeft = (Button) findViewById(R.id.rtol_btn);
        three_btn = (Button) findViewById(R.id.three_btn);
        toast_btn = (Button) findViewById(R.id.toast_btn);

        ProgressButtonListener progressButtonListener = new ProgressButtonListener();
        leftToRight.setOnClickListener(progressButtonListener);
        rightToLeft.setOnClickListener(progressButtonListener);
        three_btn.setOnClickListener(progressButtonListener);
        toast_btn.setOnClickListener(progressButtonListener);

        //최대값 설정
        progressBar_Horizen.setMax(200);
        progressBar_Horizen.setProgress(100);
        progressBar_Horizen.setSecondaryProgress(150);

        SwitchClickedListener switchClickedListener = new SwitchClickedListener();

        aSwitch.setOnCheckedChangeListener(switchClickedListener);
    }

    //스레드 만들기
    //스레드 생성 1.
    class MyThread extends Thread {
        public void run() {//실제 동작하는 함수 run
            //스레드 실행 3. 스레드가 실제로 작업
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            //스레드 실행 4. 핸들러에게 작업을 마쳤음을 보고
            handler.sendEmptyMessage(0);//what = 스레드의 번호
        }
    }

    class MyThread01 extends Thread {
        int commend=-1;
        MyThread01(int aCommend){
            commend = aCommend;
        }
        public void run() {//실제 동작하는 함수 run
            //스레드 실행 3. 스레드가 실제로 작업
            switch (commend){
                case 0:
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }
                    //스레드 실행 4. 핸들러에게 작업을 마쳤음을 보고
                    handler.sendEmptyMessage(0);//what = 스레드의 번호
                    break;
                case 1:
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }
                    //스레드 실행 4. 핸들러에게 작업을 마쳤음을 보고
                    handler.sendEmptyMessage(1);//what = 스레드의 번호
                    break;
            }
        }
    }

    //스레드 생성 2. 헨들러 만들기
    //ui관련 처리를 위해 스레드에서 메인 스레드로 요청하기 위함
    Handler handler = new Handler() {
        //스레드 실행 5. 스레드에게 작업이 종료되었다는 보고를 받음
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //스레드 실행 6. 메인 스레드 에게 일의 결과를 보고하고,
                    //메인스레드에게 작업을 요청함
                    progressBar_three.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    class SwitchClickedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    class ProgressButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ltor_btn:
                    progressBar_Horizen.incrementProgressBy(10);
                    progressBar_Horizen.incrementSecondaryProgressBy(10);
                    break;
                case R.id.rtol_btn:
                    progressBar_Horizen.incrementProgressBy(-10);
                    progressBar_Horizen.incrementSecondaryProgressBy(-10);
                    break;
                case R.id.three_btn:
                    progressBar_three.setVisibility(View.VISIBLE);
                    //스레드 실행 1. 스레드 객체 생성
                    MyThread01 myThread00 = new MyThread01(0);
                    myThread00.setDaemon(true);
                    //스레드 실행 2. 스레드 객체에 일 분담
                    myThread00.start();
                    break;
                case R.id.toast_btn:
                    MyThread01 myThread01 = new MyThread01(1);
                    myThread01.setDaemon(true);
                    myThread01.start();
                    break;
            }
        }
    }
}
