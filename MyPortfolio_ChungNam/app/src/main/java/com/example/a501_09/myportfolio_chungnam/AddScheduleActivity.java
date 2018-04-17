package com.example.a501_09.myportfolio_chungnam;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.datalist.PlaceList;
import com.example.a501_09.myportfolio_chungnam.datalist.ScheduleList;
import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.Place;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;
import com.example.a501_09.myportfolio_chungnam.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddScheduleActivity extends AppCompatActivity {
    DaoSession daoSession;
    ArrayList<Place> arrayList_Place;
    ArrayList<Schedule> arrayList_Schedule;
    TextView txt_place_name;
    Toolbar toolbar_add_schedule;
    EditText txt_time;
    EditText editText_playTime;
    TimeSetListener timeSetListener;
    int sche_hour, sche_minute, play_time = 30;
    ImageButton time_min, time_add;
    ImageView imageView_place;

    int play_hour=0,play_minute=0;
    int hour_visited,minute_visited;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        setData();
        setComponent();
        setToolbar();

    }
    private void setData(){
        daoSession = ((AppController) getApplication()).getDaoSession();
        arrayList_Schedule = ScheduleList.getInstance();
        arrayList_Place = PlaceList.getInstance();
    }

    private void setComponent() {
        txt_place_name = (TextView)findViewById(R.id.txt_place_name);
        imageView_place = (ImageView)findViewById(R.id.imageView_place);
        GregorianCalendar calendar = new GregorianCalendar();
        hour_visited = calendar.get(calendar.HOUR_OF_DAY);
        minute_visited = calendar.get(calendar.MINUTE);

        sche_hour = hour_visited;
        sche_minute = minute_visited;

        time_min = (ImageButton) findViewById(R.id.time_min);
        time_add = (ImageButton) findViewById(R.id.time_add);

        txt_time = (EditText) findViewById(R.id.txt_time);

        timeSetListener = new TimeSetListener();
        editText_playTime = (EditText) findViewById(R.id.editText_playTime);

        txt_time.setInputType(InputType.TYPE_NULL);
        txt_time.setFocusable(false);
        editText_playTime.setInputType(InputType.TYPE_NULL);
        editText_playTime.setFocusable(false);
        editText_playTime.setText(play_time + " 분");

        time_add.setOnClickListener(new CompoOncliclistener());
        time_min.setOnClickListener(new CompoOncliclistener());

        txt_time.setOnClickListener(new CompoOncliclistener());
        txt_place_name.setText(Util.getPlaceTitle(AddScheduleActivity.this));

        for(int i=0;i < arrayList_Place.size();i++){
            if(arrayList_Place.get(i).getName().equals(Util.getPlaceTitle(AddScheduleActivity.this))){
                String img_name;
                img_name = arrayList_Place.get(i).getImg_name();
                int img_id = getResources().getIdentifier("@drawable/"+img_name,"drawable",this.getPackageName());
                imageView_place.setImageResource(img_id);
            }
        }
    }

    private void setToolbar() {
        toolbar_add_schedule = (Toolbar) findViewById(R.id.toolbar_add_schedule);
        toolbar_add_schedule.setTitle("");
        toolbar_add_schedule.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar_add_schedule);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check_trip, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.toolbar_item_check_trip:
                Toast.makeText(this, "일정 추가", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    class TimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sche_hour = i;
            sche_minute = i1;
            txt_time.setText(sche_hour + "시" + " " + sche_minute + "분");
        }
    }
    public String convertTime(int play_time){
        String result;
        play_hour = play_time/60;
        play_minute = play_time % 60;
        if(play_hour == 0){
            result = play_minute + " 분";
        }else if(play_minute == 0){
            result = play_hour + " 시간";
        }else {
            result = play_hour + " 시간" + play_minute + " 분";
        }

        return result;
    }
    private Date setTimeNowToVisited(){
        Date result = new Date();
        hour_visited = sche_hour + play_hour;
        minute_visited = sche_minute + play_minute;

        if(minute_visited/60 == 1){
            hour_visited = hour_visited + 1;
            minute_visited = minute_visited - 60;
        }
        result.setHours(hour_visited);
        result.setMinutes(minute_visited);
        return result;
    }
    class CompoOncliclistener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.time_add:
                    play_time = play_time + 30;
                    editText_playTime.setText(convertTime(play_time));
                    Toast.makeText(AddScheduleActivity.this, setTimeNowToVisited().getHours()+" : "+setTimeNowToVisited().getMinutes(), Toast.LENGTH_SHORT).show();
                    break;
                    //int tcnt=0;
                    //while(play_time == 0){
                    //  play_time-=30;
                    //  tcnt++;
                    // }
                    //play_hour = tcnt/2;
                    //if(tcnt%2 != 0){
                    //  setText("30 분")
                    //
                case R.id.time_min:
                    if (play_time <= 30) {
                        Toast.makeText(AddScheduleActivity.this, "감소가 불가능 합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        play_time = play_time - 30;
                        editText_playTime.setText(convertTime(play_time));
                        Toast.makeText(AddScheduleActivity.this, setTimeNowToVisited().getHours()+" : "+setTimeNowToVisited().getMinutes(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.txt_time:
                    new TimePickerDialog(AddScheduleActivity.this, timeSetListener, sche_hour, sche_minute, true).show();
                    break;
            }
        }
    }
}