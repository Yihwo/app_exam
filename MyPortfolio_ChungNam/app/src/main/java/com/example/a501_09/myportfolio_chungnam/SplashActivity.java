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

        //UiHiddenNaviBar uiHiddenNaviBar = new UiHiddenNaviBar(SplashActivity.this);


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
        setPlaceData();
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);

        finish();
    }
    private void setPlaceData(){
        if(arrayList_place.isEmpty()){
            PortfolioQuery.insertPlace(daoSession,arrayList_place,"악휘봉",
                    "악휘봉은 괴산군 연풍면과 칠성면 경계에 위치한 해발 845m의 산으로 백두대간의 본 줄기에서 한발짝 벗어난 절경의 산이다. ",
                    "akhwi");
            PortfolioQuery.insertPlace(daoSession,arrayList_place,"희양산",
                    "희양산은 백두 대간의 산이다. 경북 문경시 가은읍과 경계를 이루는산으로 빼어난 경치와 천년고찰 봉암사(신라 헌강왕 5년 서기 879년)를 안고 있는 산이다.",
                    "huiyang");
            PortfolioQuery.insertPlace(daoSession,arrayList_place,"조항산",
                    "청천면 삼송리와 경북 문경군 농암면 궁기리 사이에 솟아있는 산으로 백두대간 주능선상의 대야산과 청화산 사이에 위치하고 있다. ",
                    "johang");
            PortfolioQuery.insertPlace(daoSession,arrayList_place,"쌍곡 계곡",
                    "쌍곡구곡은 산수가 아름다워 퇴계 이황, 송강 정철등 많은 유학자와 문인들이 즐겨 찾던 곳으로 주위에는 보배산, 칠보산, 군자산, 비학산 등 산행을 즐길수 있는 명산이 많이 있다. 쌍곡구곡의 울창한 노송의 숲과 기암계곡 사이로 흐르는 맑은 물은 호롱소, 소금강, 떡바위, 문수암, 쌍벽, 용소, 쌍곡폭포, 선녀탕, 장암 등의 쌍곡구곡을 이루고 있다. ",
                    "ssanggok");
            PortfolioQuery.insertPlace(daoSession,arrayList_place,"선유구곡",
                    "조선시대 유명한 학자 퇴계 이황이 7송정(현 송면리 송정마을)에 있는 함평 이씨댁을 찾아갔다가 산과 물, 바위, 노송 등이 잘 어우러진 절묘한 경치에 반하여 9달을 돌아다니며 9곡의 이름을 지어 새겼는데 오랜 세월이 지나는 동안 글자는 없어지고 산천만이 남아있다. ",
                    "sunyou");

        }
    }
}
