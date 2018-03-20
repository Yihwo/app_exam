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

    class MyAnsyncTask extends AsyncTask<Void,Void,Void>{
        protected void onPreExecute(){
            count_value = 0;
        }
        protected Void doInBackground(Void... arg) {//Void의 v 는 대문자로 시작,
                                                    // Void... arg = 일에 필요한 자료가 많을 수 있기 때문에 ...으로 생략
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
        //작업이 진행되는동안 체크해주는 함수
        protected void onProgressUpdate(Void... arg){
            progressBar.setVisibility(View.VISIBLE);
            txt_cont.setText(Integer.toString(count_value));
        }
        //작업을 마치고 종료
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
