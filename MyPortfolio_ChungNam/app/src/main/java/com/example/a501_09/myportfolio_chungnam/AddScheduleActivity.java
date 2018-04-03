package com.example.a501_09.myportfolio_chungnam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class AddScheduleActivity extends AppCompatActivity {
    Toolbar toolbar_add_schedule;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        setToolbar();
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
}
