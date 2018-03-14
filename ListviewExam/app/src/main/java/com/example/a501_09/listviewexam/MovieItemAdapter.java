package com.example.a501_09.listviewexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by 501-09 on 2018-03-13.
 */
public class MovieItemAdapter extends BaseAdapter {
    Context context;//메인엑티비티 정보를 담는 변수
    LayoutInflater inflater;//항목을 표현하기 위해 활용하는 객체
    ArrayList<MovieItemFormet> arrayList;//자료를 저장하고 있는 arraylist
    int item_id;//항목 Layout 파일의 아이디

    public MovieItemAdapter(Context context, ArrayList<MovieItemFormet> arrayList, int item_id) {
        this.context = context;
        this.arrayList = arrayList;
        this.item_id = item_id;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            view = inflater.inflate(item_id,viewGroup,false);
        }
        ImageView movie_img = (ImageView)view.findViewById(R.id.movie_img);
        TextView movie_title = (TextView)view.findViewById(R.id.movieTitle_txt);
        TextView movie_subtitle = (TextView)view.findViewById(R.id.subTitle_txt);

        movie_img.setImageResource(arrayList.get(i).movie_img);
        movie_title.setText(arrayList.get(i).title_txt);
        movie_subtitle.setText(arrayList.get(i).subTitle_txt);

        return view;
    }

}
