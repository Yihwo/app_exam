package com.example.a501_09.asynctaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn_start,btn_stop;
    TextView txt_cont;
    ProgressBar progressBar;
    int count_value;
    MyAnsyncTask asyncTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponent();

    }
    //asynctask객체를 생성하기 위한 클래스
    //자료형에 주의 해야 함<A,B,C>
    //A : doImBackground()함수의 매계변수 형태
    //B : onProgressUpdate()함수의 매계변수 형태
    //C : onPostExecute()함수의 매계변수 형태
    class MyAnsyncTask extends AsyncTask<Void,Void,Void>{
        //작업 시작 전에 계산을 위한 초기화, 컴포넌트값 초기화(프로그래스바 표시)를 처리하는 함수
        protected void onPreExecute(){
            count_value = 0;
        }
        //작업을 진행하는 함수, publishProgress()함수를 호출하여 진행사항을 앱 화면에 표시함
        protected Void doInBackground(Void... arg) {//Void의 v 는 대문자로 시작,
                                                    // Void... arg = 일에 필요한 자료가 많을 수 있기 때문에 ...으로 생략
            //siCancelled()작업이 최소 되었는지 확인하는 함수
            while(isCancelled()==false){
                count_value++;
                if (count_value<=1000){
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
            progressBar.setVisibility(View.VISIBLE);
            txt_cont.setText("분"+Integer.toString(count_value/60)+" "+Integer.toString(count_value%60)+"초");
        }
        //작업을 마치고 종료되었을때 호출,odInbackground()함수의 리턴값을 매개변수로 받는다.
        protected void onPostExecute(Void result){
            progressBar.setVisibility(View.INVISIBLE);
            count_value = 0;
        }
        //작업을 중단하였을때 실행되는 함수
        protected void onCancelled(){
            progressBar.setVisibility(View.INVISIBLE);
            txt_cont.setText("사용자가 중단하였습니다.");
        }
    }

    protected void setComponent(){
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        txt_cont = (TextView)findViewById(R.id.txt_cont);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(asyncTask==null) {
                    asyncTask = new MyAnsyncTask();
                    //asynctask 실행
                    asyncTask.execute();
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(asyncTask != null) {
                    asyncTask.cancel(false);
                    asyncTask = null;
                }
            }
        });
    }
}
