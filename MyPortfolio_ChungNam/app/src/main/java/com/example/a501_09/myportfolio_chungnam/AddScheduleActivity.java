package com.example.a501_09.myportfolio_chungnam;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.util.Util;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddScheduleActivity extends AppCompatActivity {
    Toolbar toolbar_add_schedule;
    Button btn_time;
    TimeSetListener timeSetListener;
    int hour,minute;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        btn_time = (Button)findViewById(R.id.btn_time);
        setToolbar();
        timeSetListener = new TimeSetListener();
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddScheduleActivity.this,timeSetListener,hour,minute,false).show();
//                new TimePickerDialog(AddScheduleActivity.this,timeSetListener,hour,minute,false).show();
            }
        });
    }
    private void setToolbar(){
        toolbar_add_schedule = (Toolbar)findViewById(R.id.toolbar_add_schedule);
        toolbar_add_schedule.setTitle("");
        toolbar_add_schedule.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar_add_schedule);
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_check_trip,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.toolbar_item_check_trip:
                Toast.makeText(this, "일정 추가", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    class  TimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Toast.makeText(AddScheduleActivity.this,i+":"+i1,Toast.LENGTH_SHORT).show();

        }
    }
}
