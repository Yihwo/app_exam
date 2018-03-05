package com.example.a501_09.listviewexam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

/**
 * Created by 501-09 on 2018-03-05.
 */

public class ReservationActivity extends AppCompatActivity {

    Button time_btn,date_btn;
    TimeSetListener timeSetListener;
    DateSetListener dateSetListener;
    GregorianCalendar calendar;
    int year, month,day,hour,minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        time_btn = (Button)findViewById(R.id.time_btn);
        date_btn = (Button)findViewById(R.id.date_btn);

        TimeDateButtonListener timeDateButtonListener = new TimeDateButtonListener();
        time_btn.setOnClickListener(timeDateButtonListener);
        date_btn.setOnClickListener(timeDateButtonListener);
        calendar = new GregorianCalendar();
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(calendar.DAY_OF_MONTH);
        hour = calendar.get(calendar.HOUR_OF_DAY);
        minute = calendar.get(calendar.MINUTE);

        Intent intent = getIntent();

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
        }
    }
    class DateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(ReservationActivity.this,i+"/"+(i1+1)+"/"+i2,Toast.LENGTH_SHORT).show();
        }
    }
}
