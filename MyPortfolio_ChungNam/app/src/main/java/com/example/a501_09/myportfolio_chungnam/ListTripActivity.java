package com.example.a501_09.myportfolio_chungnam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-03.
 */

public class ListTripActivity extends AppCompatActivity {
    Toolbar toolbar_List_trip;
    ListView listView_List_trip;
    ArrayList<Trip> arrayList_trip;
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
        listView_List_trip.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int selected_list_item=i;
                AlertDialog.Builder alert=new AlertDialog.Builder(view.getContext());
                alert.setTitle("확인");
                alert.setMessage("삭제 하시겠습니까?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PortfolioQuery.deleteTripDataById(daoSession, arrayList_trip.get(selected_list_item).getId());
                        tripListAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();

                return true;
            }
        });
    }
    private void setData(){
        daoSession = ((AppController) getApplication()).getDaoSession();
        arrayList_trip = TripList.getInstance();
    }
    private void setComponents(){
        listView_List_trip = (ListView)findViewById(R.id.listView_List_trip);


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
                Intent intent = new Intent(ListTripActivity.this,AddTripActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
