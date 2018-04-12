package com.example.a501_09.myportfolio_chungnam.datalist;

import com.example.a501_09.myportfolio_chungnam.db.Place;
import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-12.
 */

public class PlaceList {
    private static ArrayList<Place> PlaceList =
            new ArrayList<Place>();
    private PlaceList(){}
    public static synchronized ArrayList<Place> getInstance(){
        return PlaceList;
    }
}
