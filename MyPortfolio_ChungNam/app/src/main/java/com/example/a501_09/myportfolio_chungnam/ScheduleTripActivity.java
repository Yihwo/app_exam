package com.example.a501_09.myportfolio_chungnam;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class ScheduleTripActivity extends AppCompatActivity implements WeekView.EventClickListener{
    DaoSession daoSession;
    Toolbar toolbar_schedule;
    WeekView weekView;
    int trip_index;
    ArrayList<Trip> arrayList_trip;
    ArrayList<Schedule> arrayList_schedule;
    TextView txt_sche_title,txt_sche_date,txt_sche_budget,txt_sche_member,txt_left_budget;
    long allSpendMoney=0;
    long all_left_money;
    boolean bSetSchedule = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setToolbar();
        setData();
        setComponent();

        weekView = (WeekView)findViewById(R.id.weekView);

        Trip print_trip = arrayList_trip.get(trip_index);

        weekView.goToDate(getCalendarDate(print_trip.getStart_day().getYear(),
                print_trip.getStart_day().getMonth(),
                print_trip.getStart_day().getDate()));
        weekView.goToHour(6);

        weekView.setOnEventClickListener(this);
        weekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

                final String weekview_name = event.getName();

                AlertDialog.Builder alert=new AlertDialog.Builder(weekView.getContext());
                alert.setTitle("확인");
                alert.setMessage("삭제 하시겠습니까?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int j;
                        String temp;
                        for (j=0;j<arrayList_schedule.size();j++){
                            temp = arrayList_schedule.get(j).getPlace_name()+"\n"+arrayList_schedule.get(j).getSpend_money()+"원";
                            if(temp.equals(weekview_name)){
                                PortfolioQuery.deleteSchduleById(daoSession,arrayList_schedule.get(j).getId());
                            }
                        }
                        weekView.notifyDatasetChanged();
                        getLeftBudget();
                    }
                });
                alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
                //PortfolioQuery.deleteSchduleById();
            }
        });
        MonthLoader.MonthChangeListener monthChangeListener = new MonthLoader.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                return setWeekViewEvent(
                        bSetSchedule, trip_index, arrayList_schedule, newYear, newMonth);
            }
        };
        weekView.setMonthChangeListener(monthChangeListener);
    }
    private void setComponent(){
        txt_left_budget = (TextView)findViewById(R.id.txt_left_budget);
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

        getLeftBudget();

    }
    private void setData(){
        daoSession = ((AppController) getApplication()).getDaoSession();
        //화면 전환 후 이전 엑티비티에서 보낸 인텐트를 수신
        Intent intent = getIntent();
        //인텐트에서 데이터를 읽음
        trip_index = intent.getIntExtra("SELECTED_TRIP", -1);
        arrayList_trip= TripList.getInstance();
        arrayList_schedule = ScheduleList.getInstance();

    }
    private void getLeftBudget(){
        allSpendMoney = 0;
        for(int i = 0; i<arrayList_schedule.size(); i++){
            allSpendMoney = allSpendMoney + arrayList_schedule.get(i).getSpend_money();
        }
        all_left_money = arrayList_trip.get(trip_index).getTotal_money() - allSpendMoney;
        String total = String.valueOf(all_left_money);
        txt_left_budget.setText(total + "원");

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
                Intent intent = new Intent(ScheduleTripActivity.this, AddScheduleActivity.class);
                intent.putExtra("SELECTED_TRIP",trip_index);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    private Calendar getCalendarDate(int year, int month, int date) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, date);

        return cal;
    }

    private List<WeekViewEvent> setWeekViewEvent(boolean bSetSchedule,
                                                 int trip_index, ArrayList<Schedule> arrayList_schedule,int year, int month) {
        Calendar startTime;
        Calendar endTime;
        Date visit_time;
        Date elapse_time;
        int[] color_arr = {
                R.color.colorSchedule1,
                R.color.colorSchedule2,
                R.color.colorSchedule3,
                R.color.colorSchedule4,
                R.color.colorSchedule5,
                R.color.colorSchedule6,
                R.color.colorSchedule7,
                R.color.colorSchedule8
        };

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        if(!bSetSchedule) {


            ArrayList<Schedule> schedules = new ArrayList<Schedule>();
            for(int i = 0; i < arrayList_schedule.size(); i++) {

                if(arrayList_schedule.get(i).getTrip_id() ==
                        arrayList_trip.get(trip_index).getId() &&
                        arrayList_schedule.get(i).getVisit_time().getYear() == year &&
                        arrayList_schedule.get(i).getVisit_time().getMonth() == month) {
                    schedules.add(arrayList_schedule.get(i));

                }
            }

            for(int k = 0; k < schedules.size(); k++) {
                visit_time = schedules.get(k).getVisit_time();
                elapse_time = schedules.get(k).getElapse_time();

                startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_MONTH, visit_time.getDate());
                startTime.set(Calendar.HOUR_OF_DAY, visit_time.getHours());
                startTime.set(Calendar.MINUTE, visit_time.getMinutes());
                startTime.set(Calendar.MONTH, visit_time.getMonth()-1);
                startTime.set(Calendar.YEAR, visit_time.getYear());
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.DAY_OF_MONTH, elapse_time.getDate());
                endTime.set(Calendar.HOUR_OF_DAY, elapse_time.getHours());
                endTime.set(Calendar.MINUTE, elapse_time.getMinutes());
                endTime.set(Calendar.MONTH, elapse_time.getMonth()-1);
                endTime.set(Calendar.YEAR, elapse_time.getYear());

                WeekViewEvent event = new WeekViewEvent(k, schedules.get(k).getPlace_name()+"\n"+schedules.get(k).getSpend_money()+"원",
                        startTime, endTime);


                WeekViewEvent event2 = new WeekViewEvent();
                event.setColor(getResources().getColor(color_arr[(k % color_arr.length)]));
                events.add(event);

            }
            //this.bSetSchedule = true;

        }


        return events;
    }
}
