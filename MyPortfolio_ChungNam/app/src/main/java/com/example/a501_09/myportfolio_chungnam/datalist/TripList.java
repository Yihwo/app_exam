package com.example.a501_09.myportfolio_chungnam.datalist;

import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-10.
 */
    //싱글톤 모델
    //객체가 하나만 만들어지는 클래스
public class TripList {
    private static ArrayList<Trip> tripList =
            new ArrayList<Trip>();
    private TripList(){}
    public static synchronized ArrayList<Trip> getInstance(){
        return tripList;
    }
}
