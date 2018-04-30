package com.example.a501_09.myportfolio_chungnam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.a501_09.myportfolio_chungnam.datalist.ScheduleList;
import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class ListTripActivity extends AppCompatActivity {
    int page_selected_count = -1;
    Toolbar toolbar_List_trip;
    SwipeMenuListView listView_List_trip;
    ArrayList<Trip> arrayList_trip;
    ArrayList<Schedule> arrayList_schedule;
    TripListAdapter tripListAdapter;
    DaoSession daoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);
        setToolbar();
        setComponents();
        setData();
        setTripList();
        printTripAndSchedule();
    }
    private void printTripAndSchedule(){
        PortfolioQuery.logTrip("MyTrip",arrayList_trip);
        PortfolioQuery.logSchedule("MySchedule",arrayList_schedule);
    }
    private void setTripList(){
        tripListAdapter = new TripListAdapter(ListTripActivity.this,arrayList_trip,R.layout.item_trip_list);
        listView_List_trip.setAdapter(tripListAdapter);
//        listView_List_trip.setOnItemClickListener(new ListItemClickListener());

        listView_List_trip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListTripActivity.this,ScheduleTripActivity.class);
                intent.putExtra("SELECTED_TRIP",i);
                startActivityForResult(intent,1);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.LTGRAY));

                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("수 정");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_white_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView_List_trip.setMenuCreator(creator);
        listView_List_trip.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                final int selected_list_item = position;
                switch (index) {
                    case 0:
                        AlertDialog.Builder alert=new AlertDialog.Builder(listView_List_trip.getContext());
                        alert.setTitle("확인");
                        alert.setMessage("수정 하시겠습니까?");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                page_selected_count = 1;
                                Intent intent = new Intent(ListTripActivity.this,AddTripActivity.class);
                                intent.putExtra("SELECTED_TRIP",selected_list_item);
                                intent.putExtra("UPDATE_TRIP",page_selected_count);
                                startActivityForResult(intent,1);
                            }
                        });
                        alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert.show();
                        break;
                    case 1:
                        AlertDialog.Builder alert01=new AlertDialog.Builder(listView_List_trip.getContext());
                        alert01.setTitle("확인");
                        alert01.setMessage("삭제 하시겠습니까?");
                        alert01.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PortfolioQuery.deleteTripDataById(daoSession, arrayList_trip.get(selected_list_item).getId());
                                tripListAdapter.notifyDataSetChanged();
                            }
                        });
                        alert01.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert01.show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    private void setData(){
        daoSession = ((AppController) getApplication()).getDaoSession();
        arrayList_trip = TripList.getInstance();
        arrayList_schedule = ScheduleList.getInstance();
    }
    private void setComponents(){
        listView_List_trip = (SwipeMenuListView)findViewById(R.id.listView_List_trip);
    }
//    툴바 관련 함수--------------------------------------------------------------------------------------
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
                Intent intent = new Intent(ListTripActivity.this,AddTripActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
