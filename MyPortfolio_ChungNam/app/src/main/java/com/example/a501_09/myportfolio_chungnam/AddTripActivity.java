package com.example.a501_09.myportfolio_chungnam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddTripActivity extends AppCompatActivity {

    DaoSession daoSession;
    ArrayList<Trip> arrayList_Trip;

    EditText text_Trip_title;
    EditText date_Trip_start;
    EditText date_Trip_end;
    EditText text_Trip_member;
    EditText text_Trip_budget;
    ImageButton member_add;
    ImageButton member_min;

    Toolbar toolbar_addTrip;
    int start_year, start_month, start_day;
    int end_year, end_month, end_day;
    int number_of_member = 1;
    int trip_year,trip_month,trip_day;
    int trip_index;
    int page_selected_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        daoSession = ((AppController) getApplication()).getDaoSession();
        arrayList_Trip= TripList.getInstance();

        setToolbar();
        setComponents();
        Intent intent = getIntent();
        //인텐트에서 데이터를 읽음
        page_selected_count = intent.getIntExtra("UPDATE_TRIP",-1);
        trip_index = intent.getIntExtra("SELECTED_TRIP", -1);
    }

    private void setComponents() {
        GregorianCalendar calendar = new GregorianCalendar();
        trip_year = calendar.get(calendar.YEAR);
        trip_month = calendar.get(calendar.MONTH);
        trip_day = calendar.get(calendar.DAY_OF_MONTH);

        text_Trip_budget = (EditText) findViewById(R.id.text_Trip_budget);
        text_Trip_member = (EditText) findViewById(R.id.text_Trip_member);
        text_Trip_title = (EditText) findViewById(R.id.text_Trip_title);
        date_Trip_end = (EditText) findViewById(R.id.date_Trip_end);
        date_Trip_start = (EditText) findViewById(R.id.date_Trip_start);
        member_add = (ImageButton) findViewById(R.id.member_add);
        member_min = (ImageButton) findViewById(R.id.member_min);

        date_Trip_start.setInputType(InputType.TYPE_NULL);
        date_Trip_start.setFocusable(false);
        date_Trip_end.setInputType(InputType.TYPE_NULL);
        date_Trip_end.setFocusable(false);

        date_Trip_start.setOnClickListener(new AddTripListener());
        date_Trip_end.setOnClickListener(new AddTripListener());
        member_add.setOnClickListener(new AddTripListener());
        member_min.setOnClickListener(new AddTripListener());
    }

    private void setToolbar() {
        toolbar_addTrip = (Toolbar) findViewById(R.id.toolbar_addtrip);
        toolbar_addTrip.setTitle("");
        toolbar_addTrip.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar_addTrip);
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
                Date start_Date = new Date(start_year,start_month,start_day);
                Date end_Date = new Date(end_year,end_month,end_day);
                if(text_Trip_title.getText().toString().equals("")) {
                    Toast.makeText(this, "여행제목을 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(date_Trip_start.getText().toString().equals("")){
                    Toast.makeText(this, "여행시작 일자를 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(date_Trip_end.getText().toString().equals("")){
                    Toast.makeText(this, "여행종료 일자를 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else if(text_Trip_budget.getText().toString().equals("")){
                    Toast.makeText(this, "여행예산을 넣어주세요", Toast.LENGTH_SHORT).show();
                    break;
                }else {
                    Toast.makeText(this, "여행 추가", Toast.LENGTH_SHORT).show();
                    //여행 추가 01

                    if(page_selected_count == 1){
                        PortfolioQuery.updateTrip(
                                daoSession,
                                arrayList_Trip.get(trip_index).getId(),
                                text_Trip_title.getText().toString(),
                                start_Date,
                                end_Date,
                                number_of_member,
                                Long.valueOf(text_Trip_budget.getText().toString())
                        );
                    }else {
                        PortfolioQuery.insertTrip(
                                daoSession,
                                arrayList_Trip,
                                text_Trip_title.getText().toString(),
                                start_Date,
                                end_Date,
                                number_of_member,
                                Long.valueOf(text_Trip_budget.getText().toString())
                        );
                    }
                    Intent intent = new Intent(AddTripActivity.this, ListTripActivity.class);
                    startActivity(intent);
                    List<Trip> test = daoSession.getTripDao().queryBuilder().list();
                    for (int i = 0; i < test.size(); i++) {
                        String msg = "trip - " +
                                "id : " + test.get(i).getId() + " / " +
                                "title : " + test.get(i).getTitle() + " / " +
                                "start_date : " + test.get(i).getStart_day() + " / " +
                                "end_date : " + test.get(i).getEnd_day() + " / " +
                                "number_of_member : " + test.get(i).getNumber_of_member() + " / " +
                                "total_money : " + test.get(i).getTotal_money() + " / "
                                + "created_at : " + test.get(i).getCreated_at() + " / "
                                + "updated_at : " + test.get(i).getUpdate_at() + " / ";
                        Log.d("trip data", msg);
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    class AddTripListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.date_Trip_start:
                    Calendar cal_Start1 = Calendar.getInstance();
                    cal_Start1.set(trip_year,trip_month,trip_day);
                    DatePickerDialog dialog1 = new DatePickerDialog(AddTripActivity.this, dateSetListener1, trip_year, trip_month, trip_day);

                    dialog1.getDatePicker().setMinDate(cal_Start1.getTimeInMillis());    //입력한 날짜 이후로 클릭 안되게 옵션
                    dialog1.show();

                    break;
                case R.id.date_Trip_end:
                    Calendar cal_Start2 = Calendar.getInstance();
                    cal_Start2.set(start_year,start_month-1,start_day);
                    DatePickerDialog dialog = new DatePickerDialog(AddTripActivity.this, dateSetListener2, trip_year, trip_month, trip_day);

                    dialog.getDatePicker().setMinDate(cal_Start2.getTimeInMillis());    //입력한 날짜 이후로 클릭 안되게 옵션
                    dialog.show();
                    break;
                case R.id.member_add:
                    number_of_member++;
                    text_Trip_member.setText(Integer.toString(number_of_member) + "명");
                    break;
                case R.id.member_min:
                    if (number_of_member <= 1) {
                        Toast.makeText(AddTripActivity.this, "인원감소가 불가능합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        number_of_member--;
                    }
                    text_Trip_member.setText(Integer.toString(number_of_member) + "명");
                    break;
            }
        }
    }

    private DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d("myselected start day", i + "년" + i1 + "월" + i2 + "일");
            start_year = i;
            start_month = i1+1;
            start_day = i2;
            date_Trip_start.setText(start_year+" 년 "+start_month+" 월 "+start_day+" 일");
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d("myselected end day", i + "년" + i1 + "월" + i2 + "일");
            end_year = i;
            end_month = i1+1;
            end_day = i2;
            date_Trip_end.setText(end_year+" 년 "+end_month+" 월 "+end_day+" 일");
        }
    };
}
