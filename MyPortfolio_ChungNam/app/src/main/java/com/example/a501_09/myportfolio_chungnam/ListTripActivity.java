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

public class ListTripActivity extends AppCompatActivity {
    Toolbar toolbar_List_trip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);
        setToolbar();
    }
    private void setToolbar(){
        toolbar_List_trip= (Toolbar)findViewById(R.id.toolbar_List_trip);
        toolbar_List_trip.setTitle("");
        toolbar_List_trip.setNavigationIcon(R.mipmap.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar_List_trip);
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
                Toast.makeText(this, "여행 목록", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
