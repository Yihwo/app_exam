package com.example.a501_09.myportfolio_chungnam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.a501_09.myportfolio_chungnam.datalist.PlaceList;
import com.example.a501_09.myportfolio_chungnam.datalist.ScheduleList;
import com.example.a501_09.myportfolio_chungnam.datalist.TripList;
import com.example.a501_09.myportfolio_chungnam.db.DaoSession;
import com.example.a501_09.myportfolio_chungnam.db.Place;
import com.example.a501_09.myportfolio_chungnam.db.PortfolioQuery;
import com.example.a501_09.myportfolio_chungnam.db.Schedule;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 501-09 on 2018-03-30.
 */
//첫 로딩동안 나오는 화면
public class SplashActivity extends AppCompatActivity {
    DaoSession daoSession;
    ArrayList<Place> arrayList_place;
    ArrayList<Trip> arrayList_Trip;
    ArrayList<Schedule> arrayList_Schdule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UiHiddenNaviBar uiHiddenNaviBar = new UiHiddenNaviBar(SplashActivity.this);


//////////////////////////////////////////date insert////////////////////////////
        daoSession= ((AppController)getApplication()).getDaoSession();
        arrayList_place = PlaceList.getInstance();//싱글콘
        arrayList_Schdule = ScheduleList.getInstance();
        arrayList_Trip = TripList.getInstance();

        ///테이블 초기화
        //PortfolioQuery.deleteAllTableData(daoSession);

        PortfolioQuery.setInitAllData(daoSession,arrayList_place,arrayList_Schdule,arrayList_Trip);
//        //데이터 저장
//        daoSession.insert(new Place(6L,new Date(),new Date(),"청남대",
//                "대통령의별장","img1.jpg","000-0000-0000"));
//        daoSession.insert(new Place(7L,new Date(),new Date(),"청남대",
//                "대통령의집","img2.jpg","000-0000-0000"));
//
////        daoSession.getPlaceDao().queryBuilder().buildDelete();
//        //데이터 읽어오기
//        List<Place> list = daoSession.getPlaceDao().queryBuilder().list();
//
//        for(int i=0;i<list.size();i++){
//            Place temp = list.get(i);
//            Log.d("portfolio_code",temp.getName());
//        }

//        //앱을 동작하기 위한 준비를 위한 시간
//        try{
//            Thread.sleep(5000);
//            Toast.makeText(this, "5초 ", Toast.LENGTH_SHORT).show();
//        }catch(Exception e){;}

        Intent intent = new Intent(SplashActivity.this,ListTripActivity.class);
        startActivity(intent);

        finish();
    }
}
