package com.example.a501_09.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    Button time_btn;
    Button date_btn;
    DateSetListener dateSetListener;
    TimeSetListener timeSetListener;
    int year,month,day,hour,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //현재 시간과 날짜 구하기
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(calendar.DAY_OF_MONTH);
        hour = calendar.get(calendar.HOUR_OF_DAY);
        minute = calendar.get(calendar.MINUTE);


        time_btn = (Button)findViewById(R.id.timePicker_btn);
        date_btn = (Button)findViewById(R.id.datePicker_btn);

        TimeDatePickerButtonListener timeDatePickerButtonListener = new TimeDatePickerButtonListener();
        dateSetListener = new DateSetListener();
        timeSetListener = new TimeSetListener();

        time_btn.setOnClickListener(timeDatePickerButtonListener);
        date_btn.setOnClickListener(timeDatePickerButtonListener);
    }
    class TimeDatePickerButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.timePicker_btn:
                    new TimePickerDialog(MainActivity.this,timeSetListener,hour,minute,false).show();
                    break;
                case R.id.datePicker_btn:
                    new DatePickerDialog(MainActivity.this,dateSetListener, year,month,day).show();
                    break;
            }
        }
    }
    class DateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(MainActivity.this,i+"/"+(i1+1)+"/"+i2,Toast.LENGTH_SHORT).show();
        }
    }
    class  TimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Toast.makeText(MainActivity.this,i+":"+i1,Toast.LENGTH_SHORT).show();

        }
    }
}
