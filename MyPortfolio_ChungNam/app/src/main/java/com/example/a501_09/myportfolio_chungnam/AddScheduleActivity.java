package com.example.a501_09.myportfolio_chungnam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;
import com.example.a501_09.myportfolio_chungnam.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddScheduleActivity extends AppCompatActivity {
    DaoSession daoSession;
    ArrayList<Place> arrayList_Place;
    ArrayList<Schedule> arrayList_Schedule;
    ArrayList<Trip> arrayList_Trip;
    TextView txt_place_name,textView_budgetLeft;
    Toolbar toolbar_add_schedule;
    EditText txt_time,txt_visitDate;
    EditText editText_playTime,editText_budget;
    TimeSetListener timeSetListener;
    DateSetListener dateSetListener;
    int sche_hour, sche_minute, play_time = 30;
    ImageButton time_min, time_add, budget_check;
    ImageView imageView_place;

    int play_hour=0,play_minute=0;
    int hour_visited,minute_visited;
    int place_index,trip_index;
    int elapse_day,elapse_min,elapse_hour,elapse_month,elapse_year,visit_day,visit_month,visit_year;
    int trip_year,trip_month,trip_day;

    boolean isPossible = false;

    long budget_Left;
    long budget_schedule = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        setData();
        setComponent();
        setToolbar();
        editText_budget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                String temp_str = editable.toString();
                long temp_long;
                if(temp_str.equals("")){
                    temp_long=0;
                }else{
                    temp_long = Long.parseLong(temp_str);
                }
                textView_budgetLeft.setText("남은 금액"+(budget_Left - temp_long)+" 원");
            }
        });
    }
    private void setData(){
        daoSession = ((AppController) getApplication()).getDaoSession();
        arrayList_Schedule = ScheduleList.getInstance();
        arrayList_Place = PlaceList.getInstance();
        arrayList_Trip = TripList.getInstance();
        Intent intent = getIntent();
        trip_index = intent.getIntExtra("SELECTED_TRIP",-1);
        place_index = Util.getPlaceIndex(AddScheduleActivity.this);
    }

    private void setComponent() {
        GregorianCalendar calendar = new GregorianCalendar();
        trip_year = calendar.get(calendar.YEAR);
        trip_month = calendar.get(calendar.MONTH);
        trip_day = calendar.get(calendar.DAY_OF_MONTH);

        txt_place_name = (TextView)findViewById(R.id.txt_place_name);
        imageView_place = (ImageView)findViewById(R.id.imageView_place);

        hour_visited = calendar.get(calendar.HOUR_OF_DAY);
        minute_visited = calendar.get(calendar.MINUTE);

        editText_budget = (EditText)findViewById(R.id.editText_budget);
        textView_budgetLeft = (TextView)findViewById(R.id.textView_budgetLeft);

        sche_hour = hour_visited;
        sche_minute = minute_visited;

        time_min = (ImageButton) findViewById(R.id.time_min);
        time_add = (ImageButton) findViewById(R.id.time_add);
        //budget_check = (ImageButton)findViewById(R.id.budget_check);

        txt_time = (EditText) findViewById(R.id.txt_time);
        txt_visitDate = (EditText)findViewById(R.id.txt_visitDate);

        timeSetListener = new TimeSetListener();
        dateSetListener = new DateSetListener();
        editText_playTime = (EditText) findViewById(R.id.editText_playTime);

        txt_time.setInputType(InputType.TYPE_NULL);
        txt_time.setFocusable(false);
        editText_playTime.setInputType(InputType.TYPE_NULL);
        editText_playTime.setFocusable(false);
        editText_playTime.setText(play_time + " 분");
        txt_visitDate.setInputType(InputType.TYPE_NULL);
        txt_visitDate.setFocusable(false);

        time_add.setOnClickListener(new CompoOncliclistener());
        time_min.setOnClickListener(new CompoOncliclistener());
        //budget_check.setOnClickListener(new CompoOncliclistener());

        txt_time.setOnClickListener(new CompoOncliclistener());
        txt_visitDate.setOnClickListener(new CompoOncliclistener());
        txt_place_name.setText(Util.getPlaceTitle(AddScheduleActivity.this));

        convertTime(play_time);

        budget_Left = arrayList_Trip.get(trip_index).getTotal_money();
        for (int i=0; i<arrayList_Schedule.size(); i++){
            budget_Left = budget_Left - arrayList_Schedule.get(i).getSpend_money();
        }
        textView_budgetLeft.setText("남은 금액"+budget_Left+" 원");

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
                if(txt_visitDate.getText().toString().equals("")){
                    Toast.makeText(this, "여행시작 일자를 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(txt_time.getText().toString().equals("")){
                    Toast.makeText(this, "방문 시간을 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(editText_budget.getText().toString().equals("")){
                    Toast.makeText(this, "지출금액을 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(isPossible==true){
                    Toast.makeText(AddScheduleActivity.this, "예산을 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Date ElapseDate = setTimeNowToVisited();
                    Date VisitDate = new Date();
                    elapse_min = ElapseDate.getMinutes();
                    elapse_hour = ElapseDate.getHours();
                    elapse_day = visit_day;
                    elapse_month = visit_month;
                    elapse_year = visit_year;
//                elapse_day = arrayList_Trip.get(trip_index).getStart_day().getDate();
//                elapse_month = arrayList_Trip.get(trip_index).getStart_day().getMonth();
//                elapse_year = arrayList_Trip.get(trip_index).getStart_day().getYear();

                    VisitDate.setHours(sche_hour);
                    VisitDate.setMinutes(sche_minute);
                    PortfolioQuery.insertSchedule(daoSession,
                            arrayList_Schedule,
                            txt_place_name.getText().toString(),
                            new Date(elapse_year, elapse_month, elapse_day, elapse_hour, elapse_min),
                            Long.parseLong(editText_budget.getText().toString()),
                            new Date(visit_year, visit_month, visit_day, VisitDate.getHours(), VisitDate.getMinutes()),
                            arrayList_Trip.get(trip_index).getId(),
                            arrayList_Place.get(place_index).getId()
                    );

                    PortfolioQuery.logSchedule("mySchedule", arrayList_Schedule);

                    Intent intent = new Intent(AddScheduleActivity.this, ScheduleTripActivity.class);
                    intent.putExtra("SELECTED_TRIP", trip_index);
                    startActivity(intent);
                    finish();
                }
                return true;

        }
        return super.onOptionsItemSelected(menuItem);
    }
//    class BudgetMyListener implements View.OnFocusChangeListener{
//        @Override
//        public void onFocusChange(View view, boolean b) {
//            //Toast.makeText(AddScheduleActivity.this,arrayList_Trip.get(trip_index).getTotal_money()+"원", Toast.LENGTH_SHORT).show();
//
//            long result = arrayList_Trip.get(trip_index).getTotal_money();
//            if(!isStart){
//                isStart = true;
//            }else {
//                long budget = Long.valueOf(editText_budget.getText().toString());
//                result = result - budget;
//                textView_budgetLeft.setText(result + "원");
//            }
//        }
//    }
    class TimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sche_hour = i;
            sche_minute = i1;
            txt_time.setText(sche_hour + "시" + " " + sche_minute + "분");
        }
    }
    class DateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            visit_day = i2;
            visit_month = i1+1;
            visit_year = i;
            txt_visitDate.setText(visit_year+" 년 "+visit_month+" 월 "+visit_day+" 일");
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
            budget_Left = arrayList_Trip.get(trip_index).getTotal_money();
            for (int i=0; i<arrayList_Schedule.size(); i++){
                budget_Left = budget_Left - arrayList_Schedule.get(i).getSpend_money();
            }
            textView_budgetLeft.setText("남은 금액"+budget_Left+"원");
            switch (view.getId()) {
                case R.id.time_add:
                    play_time = play_time + 30;
                    editText_playTime.setText(convertTime(play_time));
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
                    }
                    break;
                case R.id.txt_time:
                    new TimePickerDialog(AddScheduleActivity.this, timeSetListener, sche_hour, sche_minute, true).show();
                    break;
                case R.id.txt_visitDate:
                    Calendar cal_Start = Calendar.getInstance();
                    Calendar cal_End = Calendar.getInstance();
                    cal_Start.set(arrayList_Trip.get(trip_index).getStart_day().getYear(),
                            arrayList_Trip.get(trip_index).getStart_day().getMonth()-1 ,
                            arrayList_Trip.get(trip_index).getStart_day().getDate());
                    cal_End.set(arrayList_Trip.get(trip_index).getEnd_day().getYear(),
                            arrayList_Trip.get(trip_index).getEnd_day().getMonth()-1,
                            arrayList_Trip.get(trip_index).getEnd_day().getDate());

                    DatePickerDialog dialog = new DatePickerDialog(AddScheduleActivity.this,dateSetListener,trip_year,trip_month,trip_day);
                    dialog.getDatePicker().setMinDate(cal_Start.getTimeInMillis());    //입력한 날짜 이후로 클릭 안되게 옵션
                    dialog.getDatePicker().setMaxDate(cal_End.getTimeInMillis());
                    dialog.show();
//                    new DatePickerDialog(AddScheduleActivity.this,dateSetListener,trip_year,trip_month,trip_day).show();
                    break;
//                case R.id.budget_check:
//                    if(editText_budget.equals("")){
//                        budget_schedule = 0;
//                    }else{
//                        budget_schedule = Long.valueOf(editText_budget.getText().toString());
//                    }
//                    if(budget_Left<budget_schedule){
//                        Toast.makeText(AddScheduleActivity.this, "예산을 초과하였습니다.", Toast.LENGTH_SHORT).show();
//                        isPossible = true;
//                    }else{
//                        budget_Left = budget_Left - budget_schedule;
//                        textView_budgetLeft.setText("남은 금액"+budget_Left+"원");
//                        isPossible = false;
//                    }
//                    break;
            }
        }
    }
}