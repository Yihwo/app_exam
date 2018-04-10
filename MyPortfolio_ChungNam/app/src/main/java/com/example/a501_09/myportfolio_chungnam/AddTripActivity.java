package com.example.a501_09.myportfolio_chungnam;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.Date;
import java.util.List;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddTripActivity extends AppCompatActivity {

    DaoSession daoSession;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        daoSession = ((AppController) getApplication()).getDaoSession();

        setToolbar();
        setComponents();
    }

    private void setComponents() {
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
                Toast.makeText(this, "여행 추가", Toast.LENGTH_SHORT).show();
                //여행 추가 01
                PortfolioQuery.insertTrip(
                        daoSession,
                        text_Trip_title.getText().toString(),
                        new Date(start_year, start_month, start_day),
                        new Date(end_year, end_month, end_day),
                        number_of_member,
                        Long.valueOf(text_Trip_budget.getText().toString())
                );
                List<Trip> test = daoSession.getTripDao().queryBuilder().list();
                for (int i = 0; i < test.size(); i++) {
                    String msg = "trip - " +
                            "id : " + test.get(i).getId() + " / " +
                            "title : " + test.get(i).getTitle() + " / " +
                            "start_date : " + test.get(i).getStart_day() + " / " +
                            "end_date : " + test.get(i).getEnd_day() + " / " +
                            "number_of_member : " + test.get(i).getNumber_of_member() + " / " +
                            "total_money : " + test.get(i).getTotal_money() + " / "
                            +"created_at : " + test.get(i).getCreated_at() + " / "
                            +"updated_at : " + test.get(i).getUpdate_at() + " / "
                    ;
                    Log.d("trip data",msg);
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
                    new DatePickerDialog(AddTripActivity.this, dateSetListener1, 2018, 4, 10).show();
                    break;
                case R.id.date_Trip_end:
                    new DatePickerDialog(AddTripActivity.this, dateSetListener2, 2018, 4, 10).show();
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
            date_Trip_start.setText(i+" 년 "+(i1+1)+" 월 "+i2+" 일");
        }
    };
    private DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d("myselected end day", i + "년" + i1 + "월" + i2 + "일");
            end_year = i;
            end_month = i1+1;
            end_day = i2;
            date_Trip_end.setText(i+" 년 "+(i1+1)+" 월 "+i2+" 일");
        }
    };
}
