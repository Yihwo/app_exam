package com.example.a501_09.listviewexam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

/**
 * Created by 501-09 on 2018-03-05.
 */

public class ReservationActivity extends AppCompatActivity {



    String[] movie_title;
    String[] movie_type;
    String[] movie_director;
    String[] movie_actor;

    Button time_btn,date_btn,refersh_btn;
    TimeSetListener timeSetListener;
    DateSetListener dateSetListener;
    GregorianCalendar calendar;
    int year, month,day,hour,minute;
    int movie_index;
    TextView mv_title,mv_dir, mv_actor, mv_type,mv_time,mv_date, txt_Seekbar;
    int mv_hour,mv_min,mv_year,mv_month,mv_day;

    SeekBar seekBar_Reserve;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mv_title = (TextView)findViewById(R.id.movie_title);
        mv_dir = (TextView)findViewById(R.id.movie_dir);
        mv_actor = (TextView)findViewById(R.id.movie_actor);
        mv_type = (TextView)findViewById(R.id.movie_type);
        mv_time = (TextView)findViewById(R.id.movie_Time);
        mv_date = (TextView)findViewById(R.id.movie_Date);

        movie_title = getResources().getStringArray(R.array.movie_title);
        movie_type = getResources().getStringArray(R.array.movie_type);
        movie_director = getResources().getStringArray(R.array.movie_director);
        movie_actor = getResources().getStringArray(R.array.movie_actor);

        time_btn = (Button)findViewById(R.id.time_btn);
        date_btn = (Button)findViewById(R.id.date_btn);
        refersh_btn = (Button)findViewById(R.id.refresh);

        TimeDateButtonListener timeDateButtonListener = new TimeDateButtonListener();
        time_btn.setOnClickListener(timeDateButtonListener);
        date_btn.setOnClickListener(timeDateButtonListener);

        timeSetListener = new TimeSetListener();
        dateSetListener = new DateSetListener();

        calendar = new GregorianCalendar();
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(calendar.DAY_OF_MONTH);
        hour = calendar.get(calendar.HOUR_OF_DAY);
        minute = calendar.get(calendar.MINUTE);

        if(minute<10) {
            mv_time.setText(hour + ":" + "0" + minute);
        }else
            mv_time.setText(hour + ":" + minute);
        mv_date.setText(year+"/"+month+"/"+day);

        seekBar_Reserve = (SeekBar)findViewById(R.id.seekBar_Reserve);
        txt_Seekbar = (TextView)findViewById(R.id.txt_Seekbar);

        RefreshButtonListener refreshButtonListener = new RefreshButtonListener();
        SeekBarlisteneer seekBarlisteneer = new SeekBarlisteneer();
        seekBar_Reserve.setOnSeekBarChangeListener(seekBarlisteneer);
        refersh_btn.setOnClickListener(refreshButtonListener);

        Intent intent = getIntent();
        movie_index = intent.getIntExtra("Movie_Index",-1);
        if(movie_index!=-1){
            Toast.makeText(ReservationActivity.this,Integer.toString(movie_index),Toast.LENGTH_SHORT).show();
            mv_title.setText(movie_title[movie_index]);
            mv_type.setText(movie_type[movie_index]);
            mv_dir.setText(movie_director[movie_index]);
            mv_actor.setText(movie_actor[movie_index]);
        }
    }
    class TimeDateButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.time_btn:
                    new TimePickerDialog(ReservationActivity.this,timeSetListener,hour,minute,true).show();
                    break;
                case R.id.date_btn:
                    new DatePickerDialog(ReservationActivity.this,dateSetListener,year,month,day).show();
                    break;
            }
        }
    }
    class TimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Toast.makeText(ReservationActivity.this,i+":"+i1,Toast.LENGTH_SHORT).show();
            if(i1<10) {
                mv_time.setText(i + ":" + "0" + i1);
            }else
                mv_time.setText(i + ":" + i1);
        }
    }
    class DateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(ReservationActivity.this,i+"/"+(i1+1)+"/"+i2,Toast.LENGTH_SHORT).show();
            mv_date.setText(i+"/"+(i1+1)+"/"+i2);
        }
    }

    class RefreshButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            seekBar_Reserve.setProgress(0);
        }
    }
    class SeekBarlisteneer implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//seekBar 의 값
            txt_Seekbar.setText("선택 좌석 : "+(i+1)+"명");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//seek에 손 대는 순간
            //Toast.makeText(MainActivity.this,"start",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//seek에 손을 뗄 때때
            //Toast.makeText(MainActivity.this,"end",Toast.LENGTH_SHORT).show();
        }
    }
}
