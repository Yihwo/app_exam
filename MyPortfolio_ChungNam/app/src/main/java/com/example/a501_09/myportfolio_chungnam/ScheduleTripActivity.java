package com.example.a501_09.myportfolio_chungnam;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.a501_09.myportfolio_chungnam.datalist.ScheduleList;
import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class ScheduleTripActivity extends AppCompatActivity
implements WeekView.EventClickListener{
    Toolbar toolbar_schedule;
    WeekView weekView;
    int trip_index;
    ArrayList<Trip> arrayList_trip;
    ArrayList<Schedule> arrayList_schedule;
    TextView txt_sche_title,txt_sche_date,txt_sche_budget,txt_sche_member;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setToolbar();
        setData();
        setComponent();

        weekView = (WeekView)findViewById(R.id.weekView);

        weekView.setOnEventClickListener(this);
        MonthLoader.MonthChangeListener monthChangeListener = new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                return new ArrayList<WeekViewEvent>();
            }
        };
        weekView.setMonthChangeListener(monthChangeListener);
    }
    private void setComponent(){
        txt_sche_title = (TextView)findViewById(R.id.txt_sche_title);
        txt_sche_date = (TextView)findViewById(R.id.txt_sche_date);
        txt_sche_member = (TextView)findViewById(R.id.txt_sche_member);
        txt_sche_budget = (TextView)findViewById(R.id.txt_sche_budget);
        Trip target = arrayList_trip.get(trip_index);
        String start_day = target.getStart_day().getYear()+"/"+
                target.getStart_day().getMonth()+"/"+
                target.getStart_day().getDate();
        String end_day = target.getEnd_day().getYear()+"/"+
                target.getEnd_day().getMonth()+"/"+
                target.getEnd_day().getDate();

        txt_sche_title.setText(target.getTitle());
        txt_sche_member.setText(target.getNumber_of_member().toString() + " 명");
        txt_sche_budget.setText(target.getTotal_money().toString() + " 원");
        txt_sche_date.setText(start_day+"~"+end_day);
    }
    private void setData(){
        //화면 전환 후 이전 엑티비티에서 보낸 인텐트를 수신
        Intent intent = getIntent();
        //인텐트에서 데이터를 읽음
        trip_index = intent.getIntExtra("SELECTED_TRIP", -1);
        arrayList_trip= TripList.getInstance();
        arrayList_schedule = ScheduleList.getInstance();
    }

    public void onEventClick(WeekViewEvent event, RectF eventRect){

    }

    private void setToolbar(){
        toolbar_schedule= (Toolbar)findViewById(R.id.toolbar_Schedule);
        toolbar_schedule.setTitle("");
        toolbar_schedule.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar_schedule);
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_trip,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.toolbar_item_add_trip:
                Toast.makeText(this, "일정 추가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScheduleTripActivity.this, AddScheduleActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
