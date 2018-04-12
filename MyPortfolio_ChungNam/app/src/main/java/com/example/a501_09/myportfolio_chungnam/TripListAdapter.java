package com.example.a501_09.myportfolio_chungnam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a501_09.myportfolio_chungnam.db.Trip;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-12.
 */

public class TripListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Trip> arrayList;
    int item_layout_id;

    public TripListAdapter(Context context, ArrayList<Trip> arrayList, int item_layout_id) {
        this.context = context;
        this.arrayList = arrayList;
        this.item_layout_id = item_layout_id;
        this.layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(item_layout_id,viewGroup,false);
        }
        TextView txt_item_trip_title = (TextView)view.findViewById(R.id.txt_item_trip_title);
        TextView txt_item_trip_days = (TextView)view.findViewById(R.id.txt_item_trip_days);
//        txt_item_trip_title.setText(arrayList.get(i).getTitle());
//        txt_item_trip_days.setText(arrayList.get(i).getStart_day().toString()+"~"+arrayList.get(i).getEnd_day().toString());
        String start_day =
                arrayList.get(i).getStart_day().getYear()+"/"+
                arrayList.get(i).getStart_day().getMonth()+"/"+
                arrayList.get(i).getStart_day().getDate();
        String end_day =
                arrayList.get(i).getEnd_day().getYear()+"/"+
                arrayList.get(i).getEnd_day().getMonth()+"/"+
                arrayList.get(i).getEnd_day().getDate();
        txt_item_trip_days.setText(start_day+"~"+end_day);
        return view;
    }
}
