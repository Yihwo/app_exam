package com.example.a501_09.myportfolio_chungnam.datalist;

import com.example.a501_09.myportfolio_chungnam.db.Schedule;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-12.
 */

public class ScheduleList {
    private static ArrayList<Schedule> ScheduleList =
            new ArrayList<Schedule>();
    private ScheduleList(){}
    public static synchronized ArrayList<Schedule> getInstance(){
        return ScheduleList;
    }
}
